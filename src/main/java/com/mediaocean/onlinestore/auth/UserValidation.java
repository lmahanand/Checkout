package com.mediaocean.onlinestore.auth;

import java.util.ArrayList;
import java.util.List;

import com.mediaocean.onlinestore.model.User;

public class UserValidation {
	public static User validateUser(String username, String password) {
		List<String> rolesList = new ArrayList<String>();
		rolesList.add("ADMIN");
		User user = new User("1",username,password,rolesList);
		String uName = "username";
		String pwd = "password";

		if (uName.equals(username) && pwd.equals(password)) {
			return user;
		} else {
			return null;
		}
	}
}
