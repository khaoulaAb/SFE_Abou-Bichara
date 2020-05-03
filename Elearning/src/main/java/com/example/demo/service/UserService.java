package com.example.demo.service;

import com.example.demo.entities.User;

public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
}
