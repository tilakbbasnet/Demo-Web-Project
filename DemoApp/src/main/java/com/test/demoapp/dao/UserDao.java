package com.test.demoapp.dao;

import com.test.demoapp.model.User;

public interface UserDao {
	public String saveUser(User user);
	public String validateUser(String username, String password);
	public User getUser(String username);
}
