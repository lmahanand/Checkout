Prequisites : Maven, Jetty or Tomcat server, Git bash

Steps to run the project

Step 1: Clone the project with the link https://github.com/lmahanand/Checkout.git
Step 2: Add a server such as tomcat and run the server by adding the project war to it.
Step 3: Once the server is up and running check whether it is working by accessing the link http://localhost:8080/checkout-counter/webapi/resource.
Step 4: Now run the client code CheckoutClient.java which has input set in there.
Step 5: Project could be executed by using user interface such as chrome post man by selecting the post opti(https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) 
		And put the below json content in the request body.
		
		[
    		{"productCode":"1", "productName":"ProdA", "productCategory":"A", "quantity":"1", "price":"100"},
    		{"productCode":"2", "productName":"ProdB", "productCategory":"B", "quantity":"2", "price":"50"},
    		{"productCode":"3", "productName":"ProdC", "productCategory":"C", "quantity":"3", "price":"40"}
		]
		
		

