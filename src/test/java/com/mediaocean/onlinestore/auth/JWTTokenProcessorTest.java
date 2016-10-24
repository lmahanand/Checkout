package com.mediaocean.onlinestore.auth;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class JWTTokenProcessorTest {
	JWTTokenProcessor processor;
	@Before
	public void setUp() throws Exception {
		processor = new JWTTokenProcessor();
	}

	@Test
	public void getToken() {
		Response res = processor.getToken("username", "password");
		assertNotNull(res);
	}
	
	@Test
	public void verifyToken(){
		
	}
}
