package com.mediaocean.onlinestore.resource.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;
import com.mediaocean.onlinestore.domain.model.ProductCost;
import com.mediaocean.onlinestore.model.StatusMessage;

public class RestClient {
	
	static Logger logger = Logger.getLogger(RestClient.class);

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/checkout-counter/webapi/resource");

		//String message = baseTarget.request().get(String.class);

		//logger.info(message);

		// Authentication of the client begins here

		WebTarget authTarget = baseTarget.path("authenticate");
		
		javax.ws.rs.core.Response authResponse = authTarget.request()
				.header("username", "username")
				.header("password", "password")
				.buildGet().invoke();

		if (authResponse.getStatus() != 200) {
			
			StatusMessage statusMessage = authResponse.readEntity(StatusMessage.class);
			System.out.println("Error Message: " + statusMessage.getMessage());
			System.out.println("Status Code: " + statusMessage.getStatus());
			
		} else {
			String jwt = authResponse.readEntity(String.class);

			//logger.info("JSON webtoken: " + jwt);
			
			WebTarget billTarget = baseTarget.path("itemizedBill");
			
			Product[] products = new Product[3];

			Product p1 = new Product(1, "A", "ProdA", 3, 100);
			Product p2 = new Product(2, "B", "ProdB", 7, 50);
			Product p3 = new Product(3, "C", "ProdC", 2, 40);

			products[0] = p1;
			products[1] = p2;
			products[2] = p3;
			
			Response billResponse = billTarget.request(MediaType.APPLICATION_JSON).header("token", jwt).buildPost(Entity.json(products)).invoke();
			ItemizedBill itemizedBill = billResponse.readEntity(new GenericType<ItemizedBill>() {
			});
						
			ProductCost[] productCost=itemizedBill.getProductCost();
			String subTotal = itemizedBill.getSubTotal();
			String totalTax = itemizedBill.getTotalTax();
			String totalBill = itemizedBill.getTotalBill();
			System.out.println("\t\t\tITEMIZED BILL");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Sr No.\tProduct Name\tQuantity\tUnit Price\tTax\tPrice");
			System.out.println();
			int srNo=1;
			for(ProductCost pc: productCost){
				System.out.println(srNo+"\t"+pc.getProductName()+"\t\t"+pc.getQuantity()+"\t\t"+pc.getUnitPrice()+"\t\t"+pc.getTaxLevied()+"\t"+pc.getTotalPrice());
				srNo++;
			}
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Sub Total\t\t\t\t\t\t\t"+subTotal);
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Total Tax\t\t\t\t\t\t\t"+totalTax);
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Total Bill\t\t\t\t\t\t\t"+totalBill);
		}

	}

}
