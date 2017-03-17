
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                POST to /v2/users/createWithList
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v2/users/createWithList'
 
        body ("""

[
    {
        "id": 0,
        "username": "someUsername",
        "firstName": "someFirstName",
        "lastName": "someLastName",
        "email": "someEmail",
        "password": "somePassword",
        "phone": "somePhone",
        "userStatus": 0
    }
]
            
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
}
		