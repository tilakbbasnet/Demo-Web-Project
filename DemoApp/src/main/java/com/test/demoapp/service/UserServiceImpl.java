package com.test.demoapp.service;

import org.springframework.stereotype.Service;

import com.test.demoapp.dao.UserDao;
import com.test.demoapp.dao.UserDaoImpl;
import com.test.demoapp.model.User;

@Service
public class UserServiceImpl implements UserService{
	UserDaoImpl userDao = new UserDaoImpl();
	public String saveUser(User user) {
		return userDao.saveUser(user);
	}

	public String validateUser(String username, String password) {
		return userDao.validateUser(username, password);
	}

	public User getUser(String username) {
		return userDao.getUser(username);
	}
}
