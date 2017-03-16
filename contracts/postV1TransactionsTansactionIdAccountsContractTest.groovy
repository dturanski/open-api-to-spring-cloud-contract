
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
Add an account to the transaction but once added it can only be updated, not removed or updated.
Only one account can be added to a transaction.
An account isn't created - it is only associated here.
If an account was previously added, then this will result in an error. (See Error Responses).
Validation of transaction and accounts will be performed.
Business rules/logic will be executed to ensure this operation can be executed.


            Given:
                POST to /v1/transactions/{tansactionId}/accounts
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/someTansactionId/accounts'
 
        body ("""

{
    "accountNumber": "someAccountNumber"
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
		