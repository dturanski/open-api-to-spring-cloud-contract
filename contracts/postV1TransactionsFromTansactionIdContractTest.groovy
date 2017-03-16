
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
It can copy transactions that is governed by business rules/logic e.g. new business transaction that is unissued. 
All  types of copy operations are supported via this API. Business rules/logic will be executed to ensure this operation can be executed. 
Transactions are a container that tracks and manages the overall processing.


            Given:
                POST to /v1/transactions/from/{tansactionId}
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/from/someTansactionId'
 
        body ("""

null
            
""")
         
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		