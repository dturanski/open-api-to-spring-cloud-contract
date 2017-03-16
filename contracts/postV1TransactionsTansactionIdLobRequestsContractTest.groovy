
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
The Request Command endpoint creates a new request. The Policy is optional and can be added later. 


            Given:
                POST to /v1/transactions/{tansactionId}/lobRequests
            When:
				
            And:
				
            Then:
			
        """)
        method 'POST'
        url '/v1/transactions/someTansactionId/lobRequests'
 
        body ("""

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
		