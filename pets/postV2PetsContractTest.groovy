
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                POST to /v2/pets
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v2/pets'
 
        body ("""

{
    "id": 0,
    "category": {
        "id": 0,
        "name": "someName"
    },
    "name": "someName",
    "photoUrls": [
        null
    ],
    "tags": [
        null
    ],
    "status": "someStatus"
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
		