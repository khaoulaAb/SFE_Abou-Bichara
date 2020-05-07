package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public Set<User> getUsers() {
		Set<User> user=new HashSet<>();
		userRepository.findAll().iterator().forEachRemaining(user::add);
		return user;
	}

	@Override
	public User findById(Long id) {
		Optional<User> userOp = userRepository.findById(id);
		if(!userOp.isPresent()){
			throw  new RuntimeException("User Not Fount");
		}
		return  userOp.get();
	}

	@Override
	public User createUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActived(true);
		User newUser= userRepository.save(user);

		return newUser;
	}

	@Override
	public User updateUser(Long id, User user) {
		User Olduser= findById(id);

		Olduser.setNom(user.getNom());
		Olduser.setPrenom(user.getPrenom());
		Olduser.setGenre(user.getGenre());
		Olduser.setEmail(user.getEmail());
		Olduser.setPassword(encoder.encode(user.getPassword()));
		Olduser.setRole(user.getRole());
		userRepository.save(Olduser);
		return Olduser;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(findById(id));
	}

	@Override
	public Set<User> getUserByRole(Role role) {
		Set<User> allUsers = getUsers();
		Set<User> filteredUsers= new HashSet<>();
		for(User u : allUsers){
			if (u.getRole().equals(role)) {
				filteredUsers.add(u);
			}

		}
		return  filteredUsers;
	}
}
