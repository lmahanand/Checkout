Prequisites : Java 1.7, Maven, Jetty or Tomcat server, Git bash

Steps to run the project

Step 1: Clone the project with the link https://github.com/lmahanand/Checkout.git
Step 2: Import the project to any IDE such as Eclipse and add a server such as tomcat and run the server by adding the project war to it.
Step 3: Once the server is up and running check whether it is working by accessing the link http://localhost:8080/checkout-counter/webapi/resource.
Step 4: Now run the client code RestClient.java which performs the below mentioned operations in sequence to get itemized bill.
		
		* Authenticates the user by passing the username and password.
		* If the authentication fails then appropriate response is sent, otherwise proceeds to next step.
		* A Json Web Token (JWT) is created by the service provider. This token actually a claim used by the service to identify the authenticated users.
		* Once the token is authenticated and verified, an itemized bill is generated against the product details mentioned in RestClient.java file.
		
		
Using Google Chrome Postman
---------------------------

Step 1: Once the server is up and running, put the link http://localhost:8080/checkout-counter/webapi/resource/ against GET method and if it succeeds it will display a message as "Checkout counter is up and running!".
Step 2: Authentication has to be peformed by passing username and password in header of the GET method to link http://localhost:8080/checkout-counter/webapi/resource/authenticate. And it will generated the JWT token.
Step 3. Pass the JWT token in header in the link http://localhost:8080/checkout-counter/webapi/resource/itemizedBill with Post method and also include below json input in the body.

		[
    		{"productCode":"1", "productName":"ProdA", "productCategory":"A", "quantity":"1", "price":"100"},
    		{"productCode":"2", "productName":"ProdB", "productCategory":"B", "quantity":"2", "price":"50"},
    		{"productCode":"3", "productName":"ProdC", "productCategory":"C", "quantity":"3", "price":"40"}
		]
		
		Once the service returns it will return the output in below json format.
		
		{
  "productCost": [
    {
      "productName": "ProdA",
      "quantity": "1",
      "taxLevied": "10.01",
      "totalPrice": "110.01",
      "unitPrice": "100.0"
    },
    {
      "productName": "ProdB",
      "quantity": "2",
      "taxLevied": "20.01",
      "totalPrice": "120.01",
      "unitPrice": "50.0"
    },
    {
      "productName": "ProdC",
      "quantity": "3",
      "taxLevied": "0.00",
      "totalPrice": "120.00",
      "unitPrice": "40.0"
    }
  ],
  "subTotal": "320.00",
  "totalBill": "350.01",
  "totalTax": "30.01"
}

