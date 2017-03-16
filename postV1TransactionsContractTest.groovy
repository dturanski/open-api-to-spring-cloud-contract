
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
This is the aggregate root of the API. If only one LOB request exists under the transaction then a monoline app request is created. 
A multi-line app request contains more than one 'lob requests'. Each 'lob request' has an associated policy app/lob. This end-point contains a single request or multiple requests - and executes the StartRequestCommand. 
The Multiline Request endpoint creates a new multi-line app request (and child requests if provided). This API should be used for all requests (monoline app or multi-line apps). This is the  Multi-Line App Request Aggregate Root.


            Given:
                POST to /v1/transactions
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions'
 
        body ("""

{
    "transactionId": "someTransactionId",
    "status": "someStatus",
    "transactionType": "someTransactionType",
    "accountNumber": {
        "accountNumber": "someAccountNumber"
    },
    "agency": {
        "agencyNumber": "someAgencyNumber"
    },
    "lobRequests": [
        {
            "lobCd": "someLobCd",
            "status": "someStatus",
            "startDate": "2017-01-01",
            "completionDate": "2017-01-01",
            "policy": {
                "policyId": "somePolicyId",
                "lobCd": "someLobCd",
                "policyTerm": 0
            },
            "underwritinginstructions": [
                {
                    "instructionType": "someInstructionType",
                    "instructionDate": "2017-01-01T00:00:00.0",
                    "instructionRemark": "someInstructionRemark"
                }
            ]
        }
    ]
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
		