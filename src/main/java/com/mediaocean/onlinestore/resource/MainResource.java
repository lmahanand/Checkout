package com.mediaocean.onlinestore.resource;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mediaocean.onlinestore.api.facade.BillingService;
import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;

@Component
@Path("resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MainResource {
	@Autowired
	@Qualifier(value = "billingService")
	private BillingService billingService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Checkout counter is up and running!";
	}

	@POST
	@Path("itemizedBill")
	public Response calculateItemizedBill(final Product[] products) {

		ItemizedBill itemizedBill = new ItemizedBill();
		itemizedBill = billingService.generateItemizedBill(Arrays.asList(products));
		return Response.status(201).entity(itemizedBill).build();
	}
}
