package com.mediaocean.onlinestore.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mediaocean.onlinestore.model.User;

public class UserValidationTest {
	@Test
	public void validateUser() {
		User user = UserValidation.validateUser("username", "password");
		assertNotNull(user);

		assertEquals("1", user.getId());
	}
}
