package com.test.demoapp.service;

import com.test.demoapp.model.User;

public interface UserService {
	public String saveUser(User user);

	public String validateUser(String username, String password);
	
	public User getUser(String username);
}
