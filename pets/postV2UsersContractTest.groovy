
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
This can only be done by the logged in user.

            Given:
                POST to /v2/users
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v2/users'
 
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
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		