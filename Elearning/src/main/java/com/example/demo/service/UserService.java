package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    Set<User> getUsers();

    User findById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void  deleteUser(Long id);

    Set<User> getUserByRole(Role role);

}
