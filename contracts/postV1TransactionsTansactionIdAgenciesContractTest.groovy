
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
Add an agency to the transaction but once added it can only be updated, not removed or updated via this API.
Checks will be made to ensure that no previous agency is associated.
An agency is not created, rather an existing agency is associated to the transaction.


            Given:
                POST to /v1/transactions/{tansactionId}/agencies
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/someTansactionId/agencies'
 
        body ("""

{
    "agencyNumber": "someAgencyNumber"
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
		