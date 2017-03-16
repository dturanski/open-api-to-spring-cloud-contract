
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
The ine Policy Command endpoint creates a new request. The Policy is optional and can be added later. Adding a ine policy will result in an event 'policy addede to request' event and will be consumed by the Policy domain.


            Given:
                POST to /v1/transactions/{transactionId}/lobRequests/{lobCd}/policies
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/someTransactionId/lobRequests/someLobCd/policies'
 
        body ("""

{
    "policyId": "somePolicyId",
    "lobCd": "someLobCd",
    "policyTerm": 0
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
		