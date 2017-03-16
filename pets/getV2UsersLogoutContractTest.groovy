
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                GET to /v2/users/logout
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/users/logout'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		