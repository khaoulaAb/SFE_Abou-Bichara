package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public List<User> getListUsers(HttpServletRequest httpServletRequest) {
		List <User> users = userRepository.findAllOrderByDate();
		List<User> listResult = new ArrayList<>();
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String email = securityContext.getAuthentication().getName();
		User u = new User();
		for (User user : users) {
			if(!user.getEmail().equals(email)){
				listResult.add(user);
			}

		}

		return listResult;
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
		user.setCreatedDate(new Date());
		user.setActived(true);
		User newUser= userRepository.save(user);

		return newUser;
	}

	@Override
	public User updateUser(User user) {
		user.setCreatedDate(new Date());

		user.setPassword(encoder.encode(user.getPassword()));
		User UpUser =userRepository.save(user);
		return  UpUser;
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

	@Override
	public Set<User> getUsers() {
        Set<User> user=new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(user::add);
        return user;
	}



	@Override
	public User getUserConnect(HttpServletRequest httpServletRequest) {

		List<User> users = userRepository.findAll();

		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String email = securityContext.getAuthentication().getName();
		User u = new User();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				u = user;
			}
		}
		return u;

	}
}