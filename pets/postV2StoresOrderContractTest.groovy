
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
    "shipDate": "2017-03-17T10:54:21.332",
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
        body ("""

{
    "id": 0,
    "petId": 0,
    "quantity": 0,
    "shipDate": "2017-03-17T10:54:21.334",
    "status": "someStatus",
    "complete": true
}
            
""")
        headers {
          header('Content-Type', 'application/json')
        }
        		  
	 }
   }
}
		