package com.mediaocean.onlinestore.resource;

import java.io.IOException;
import java.util.Arrays;

import javax.json.stream.JsonGenerationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mediaocean.onlinestore.api.facade.BillingService;
import com.mediaocean.onlinestore.auth.JWTTokenProcessor;
import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;
import com.mediaocean.onlinestore.model.StatusMessage;

@Component
@Path("resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MainResource {

	static Logger logger = Logger.getLogger(MainResource.class);

	@Autowired
	@Qualifier(value = "billingService")
	private BillingService billingService;

	@Autowired
	@Qualifier(value = "tokenProcessor")
	private JWTTokenProcessor tokenProcessor;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Checkout counter is up and running!";
	}

	@Path("authenticate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(@HeaderParam("username") String username,
			@HeaderParam("password") String password) throws JsonGenerationException, IOException {
		logger.info("Authenticating user credentials and creating JWT token.");
		Response response = tokenProcessor.getToken(username, password);
		return response;

	}

	@POST
	@Path("itemizedBill")
	public Response calculateItemizedBill(@HeaderParam("token") String token, final Product[] products)
			throws JsonGenerationException, IOException {

		logger.info("Inside calculateItemizedBill...");

		if (token == null) {
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied.");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

		boolean isTokenVerified = tokenProcessor.veriftyToken(token);

		if (isTokenVerified) {
			ItemizedBill itemizedBill = new ItemizedBill();
			itemizedBill = billingService.generateItemizedBill(Arrays.asList(products));
			return Response.status(201).entity(itemizedBill).build();
		} else {
			logger.info("User verification failed since JWT is Invalid: ");
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMessage.setMessage("Access Denied.");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}

	}

}
