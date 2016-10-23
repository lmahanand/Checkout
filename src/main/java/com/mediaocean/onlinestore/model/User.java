package com.mediaocean.onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String id;
	private String username;
	private String password;
	private List<String> rolesList = new ArrayList<String>();

	public User() {
	}

	public User(String id, String username, String password, List<String> rolesList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rolesList = rolesList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<String> rolesList) {
		this.rolesList = rolesList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", rolesList=" + rolesList
				+ "]";
	}

}
