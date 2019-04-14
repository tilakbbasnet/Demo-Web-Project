package com.test.demoapp.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {
	static BCryptPasswordEncoder bCryptPassEncoder; 
	
	public BCryptEncoder() {
		bCryptPassEncoder= new BCryptPasswordEncoder(10);
	}

	public String passwordEncoder(String password) {
		return bCryptPassEncoder.encode(password);	
	}

	public boolean passwordMatcher(String password, String encodedPassword) {
		return bCryptPassEncoder.matches(password, encodedPassword);
	}
}
