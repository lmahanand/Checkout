package com.mediaocean.onlinestore.resource.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;
import com.mediaocean.onlinestore.domain.model.ProductCost;

public class CheckoutClient {

	public static void main(String[] args) {
		WebTarget target;
		ClientConfig config;
		Client client;
		Response response;

		Product[] products = new Product[3];

		Product p1 = new Product(1, "A", "ProdA", 1, 100);
		Product p2 = new Product(2, "B", "ProdB", 2, 50);
		Product p3 = new Product(3, "C", "ProdC", 3, 40);

		products[0] = p1;
		products[1] = p2;
		products[2] = p3;

		

		config = new ClientConfig();
		client = ClientBuilder.newClient(config);
		target = client.target("http://localhost:8080/checkout-counter/webapi/resource/itemizedBill");

		response = target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(new Gson().toJson(products), MediaType.APPLICATION_JSON), Response.class);

		ItemizedBill itemizedBill = response.readEntity(new GenericType<ItemizedBill>() {
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
