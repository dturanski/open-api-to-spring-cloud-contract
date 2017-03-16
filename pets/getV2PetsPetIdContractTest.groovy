
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
Returns a pet when ID < 10.  ID > 10 or nonintegers will simulate API error conditions

            Given:
                GET to /v2/pets/{petId}
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/pets/0'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		