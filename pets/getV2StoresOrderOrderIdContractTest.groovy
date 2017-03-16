
package contracts.transactions

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
       description("""
For valid response try integer IDs with value <= 5 or > 10. Other values will generated exceptions

            Given:
                GET to /v2/stores/order/{orderId}
            When:
				
            And:
				
            Then:
			
        """)
        method 'GET'
        url '/v2/stores/order/someOrderId'
 
       headers {
          header('Content-Type', 'application/json')
          header('Accept','application/json')
       }
   }
      
   response {
       status 200
   }
}
		