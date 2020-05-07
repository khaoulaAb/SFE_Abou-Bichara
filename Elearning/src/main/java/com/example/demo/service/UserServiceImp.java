package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;



	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User find(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
