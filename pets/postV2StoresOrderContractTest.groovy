
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                POST to /v2/stores/order
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v2/stores/order'
 
        body ("""

{
    "id": 0,
    "petId": 0,
    "quantity": 0,
    "shipDate": "2017-01-01T00:00:00.0",
    "status": "someStatus",
    "complete": true
}
            
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
		