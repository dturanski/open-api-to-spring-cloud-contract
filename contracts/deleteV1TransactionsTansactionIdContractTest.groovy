
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
Deletes the transaction based on the ID - but this should be expected to be a logical delete. It maybe deleted in the future.

            Given:
                DELETE to /v1/transactions/{tansactionId}
            When:
				
            And:
				
            Then:
			
        """)
        method 'DELETE'
        url '/v1/transactions/someTansactionId'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		