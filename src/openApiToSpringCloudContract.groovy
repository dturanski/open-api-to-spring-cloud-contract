@GrabConfig( )
@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml
class openApiToSpringCloudContract {
   static main(String... args){
	   if (args.length < 2) {
		   System.err.println("Usage: openApiToSpringCloudContract [openAPISpecFile.yaml] [outputDir]")
		   System.exit(1)
	   }
	   def fileName = args[0]
	   def outputDirName = args[1]
	   def outputDir = new File(outputDirName)
	   if (!outputDir.exists()) {
		   	outputDir.mkdir()
	   }
	   new OpenApi2SpringCloudContractGenerator().generateSpringCloudContractDSL(fileName,outputDirName)
   } 
}

class OpenApi2SpringCloudContractGenerator {
	
	def generateSpringCloudContractDSL(filename, outputDirName) {
		
		println "reading ${filename}..."
		
		Yaml parser = new Yaml()
		Map openApiSpec = parser.load((filename as File).text)
		
		def paths = openApiSpec.paths
		paths.each {path ->
			def contract  = generateContractForPath(openApiSpec, path)
			
			def basePath = openApiSpec.basePath
			def endpoint = "${basePath}${path.key}"
			def httpMethod = path.value.keySet()[0]
			
			def fileName = fileNameForEndpoint(outputDirName,httpMethod,endpoint)
			
			println "writing ${fileName} ..."
			def contractFile = 
			new File(fileName).withOutputStream { stream ->
				stream << contract
			}	
		}
	}
	
    /*
     * Generate Contract DSL for each specified path	
     */
	def generateContractForPath(openApiSpec, path) {
		def basePath = openApiSpec.basePath
		def endpoint = "${basePath}${path.key}"
		def httpMethod = path.value.keySet()[0]
		
		 injectParamsIntoEndpoint(endpoint, path.value[httpMethod]);

		def contract = """
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description(\"\"\"
${path.value[httpMethod].description}

            Given:
                ${httpMethod.toUpperCase()} to ${endpoint}
            When:
				
            And:
				
            Then:
			
        \"\"\")
        method \'${httpMethod.toUpperCase()}\'
        url \'${injectParamsIntoEndpoint(endpoint, path.value[httpMethod])}\'\n"""
		if (httpMethod == 'post' || httpMethod == 'put'){
		  contract = contract + """ 
        body (\"\"\"\n
${generateSampleJsonForReqestBody(openApiSpec.definitions, path.value[httpMethod])}
            \n\"\"\")
        """
		}
		contract = contract + """ 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		"""
	   return contract
	}
	
	/*
	 * Generate a sample JSON document for the Request body
	 */
	def generateSampleJsonForReqestBody(schemaDefinitions, pathSpec) {
		def schema = pathSpec.parameters.find{it.in == 'body'}?.schema
		if (schema){
		    def schemaType = (schema.type == 'array') ? schema.items : schema 
			def bodyType = schemaTypeFromRef(schemaType)
			def builder = new groovy.json.JsonBuilder()
			def content = [schemaToJsonExample(builder, schemaDefinitions,bodyType)]
			return (schema.type == 'array') ?
			new groovy.json.JsonBuilder(content).toPrettyString() : builder.toPrettyString()
		}	
	}
	
	/*
	 * Substitute sample values for path variable placeholders
	 */
	def injectParamsIntoEndpoint(endpoint, pathSpec) {
		def pathParams = (pathSpec.parameters as List).findAll{it.in == 'path'}
		
		def tokens = endpoint.split('/')
		def endpointWithInjectVariables = tokens.collect { tok ->
			if (tok.startsWith('{') && tok.endsWith('}')) {
				def variableName = tok.replaceAll("[\\[\\](){}]","")
				def pathParam = pathParams.find{it.name == variableName}
				if (!pathParam) {
					throw new RuntimeException("path variable '$variableName' in endpoint path $endpoint not declared as a path parameter")
				}
				if (pathParam.type=='string') {
					return "some${variableName.capitalize()}"
				}
				else if (pathParam.type=='number' || pathParam.type == 'integer') {
					return 0
				}
			}
			return tok
		}
		
		def resourcePath =  endpointWithInjectVariables.join('/')
		
		def queryParams = (pathSpec.parameters as List).findAll{it.in == 'query'}
		
		
		queryParams.eachWithIndex {qp, i ->
			 
			def delim = (i == 0) ? '?' : '&'			
			def name = qp.name 
			def val
			if (qp.type == 'array') { 
				val = sampleValueForSimpleTypeField(name, qp.items)
				if (qp.collectionFormat == 'multi') {
					name = name + '[]'
					resourcePath = "${resourcePath}${delim}${name}=${val}1&${name}=${val}2"
				}
				else {
				    println "WARNING: collectionFormat ${qp.collectionFormat} not supported"
				}
			}
			else {
			   val = sampleValueForSimpleTypeField(name, qp)
			   resourcePath = "${resourcePath}${delim}${name}=${val}"
			}
			
			
		}
		
		return resourcePath
	}
	
	/*
	 * Generate a fileName for the endpoint
	 */
	def fileNameForEndpoint(outputDirName, httpMethod, endpoint) {
		def tokens = endpoint.split('/')
		def caps = tokens.findAll{!it.empty}.collect { tok-> 
				def cap = tok.replaceAll("[\\[\\](){}]","") as CharSequence
				cap.capitalize()
		}
		def filename = "${outputDirName}/${httpMethod + caps.join('')}ContractTest.groovy"
		return filename
	}
	
	/*
	 * Recursively generate sample JSON document for a declared schema type
	 */
	def schemaToJsonExample(builder, schemaDefinitions, type) {
		def schemaProperties = schemaDefinitions["${type}"].properties
		
		def result = builder  { 
			schemaProperties.each {name,schema ->
				 if (schema.type  && schema.type != 'array') {
					 "$name"  sampleValueForSimpleTypeField(name, schema)		
				 }
				 if (schema.type == 'array') {
					 def array = []
					 if (schema['$ref']){
					   "$name" array << schemaToJsonExample(builder, schemaDefinitions, schemaTypeFromRef(schema.items))
					 } 
					 else {
					    "$name" array << sampleValueForSimpleTypeField(name, schema)							
                     }					 
				 } 
				 else if (schema['$ref']) { 
					"$name"  schemaToJsonExample(builder, schemaDefinitions, schemaTypeFromRef(schema))
				 }
				 
			}
		 
		}
		
		return result
		
	}
	
	/*
	 * Parse the schema type name from the $ref attribute
	 */
	def schemaTypeFromRef(schema) {
	     
		(schema['$ref'] =~ /#\/definitions\/(.+)/)[0][1]
	}
	
	/*
	 * Generate a sample value for a simple type field
	 */
	def sampleValueForSimpleTypeField(name, property) {

		def type = property.type
		def format = property.format
		if (type == 'string') {
			return applyFormatToStringField(format, name)
		}
		else if (type == 'number' || type == 'integer') {
			return 0
		}
		else if (type == 'boolean') {
		    return true
		}
	}
	
	/*
	 * Apply format to string field
	 */
	def applyFormatToStringField(format,name) {
		if (format == 'date-time'){
			return "2017-01-01T00:00:00.0"
		}
		else if (format == 'date') {
			return "2017-01-01"
		}
		return "some${name.capitalize()}"
	}
}


