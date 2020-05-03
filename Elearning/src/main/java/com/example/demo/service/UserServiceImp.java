package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
	

	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActived(true);
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return false;
	}

}
