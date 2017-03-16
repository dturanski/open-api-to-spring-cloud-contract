
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
The request may have instructions from underwriters that are added to the request. E.g. "Policy Block" can be added with remarks.


            Given:
                POST to /v1/transactions/{transactionId}/lobRequests/{lobCd}/underwritingInstructions
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/someTransactionId/lobRequests/someLobCd/underwritingInstructions'
 
        body ("""

{
    "instructionType": "someInstructionType",
    "instructionDate": "2017-01-01T00:00:00.0",
    "instructionRemark": "someInstructionRemark"
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
		