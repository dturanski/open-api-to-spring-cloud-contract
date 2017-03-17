
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""


            Given:
                GET to /v2/users/{username}
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/users/someUsername'
 
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
    "username": "someUsername",
    "firstName": "someFirstName",
    "lastName": "someLastName",
    "email": "someEmail",
    "password": "somePassword",
    "phone": "somePhone",
    "userStatus": 0
}
            
""")
        headers {
          header('Content-Type', 'application/json')
        }
        		  
	 }
   }
}
		