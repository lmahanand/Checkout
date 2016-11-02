package com.mediaocean.onlinestore.auth;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mediaocean.onlinestore.model.User;

public class UserValidation {
	static Logger logger = Logger.getLogger(UserValidation.class);

	public static User validateUser(String username, String password) {
		List<String> rolesList = new ArrayList<String>();
		rolesList.add("ADMIN");
		User user = new User("1", username, getEncryptedPassword(password), rolesList);

		if (verifyUser(password, username)) {
			return user;
		} else {
			return null;
		}
	}

	private static boolean verifyUser(String password, String username) {

		try {
			ClassLoader classLoader = new UserValidation().getClass().getClassLoader();
			URL url = classLoader.getResource(username.concat(".des"));
			if(url==null){
				return false;
			}
			File file = new File(url.getFile());

			if (!file.exists()) {
				return false;
			}
			String hesh = new String(Files.readAllBytes(file.toPath()));

			String ecryptedPassword = getEncryptedPassword(password);

			if (hesh.equals(ecryptedPassword)) {
				return true;
			}

		} catch (IOException e) {
			
			System.out.println("Failed to read user credential file: "+ e);
			logger.error("Failed to read user credential file: ", e);
		}

		return false;
	}

	@SuppressWarnings("restriction")
	private static String getEncryptedPassword(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			return new sun.misc.BASE64Encoder().encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("Failed to encrypt password.", e);
		}
		return "";
	}
}
