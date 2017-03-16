
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.

            Given:
                GET to /v2/pets/findByTags
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/pets/findByTags?tags[]=someTags1&tags[]=someTags2'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		