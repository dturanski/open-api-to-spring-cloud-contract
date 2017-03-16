
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                GET to /v2/users/login
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/users/login?username=someUsername&password=somePassword'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		