package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    List<User> getListUsers(HttpServletRequest httpServletRequest);

    User findById(Long id);

    User createUser(User user);

    User updateUser( User user);

    void  deleteUser(Long id);

    Set<User> getUserByRole(Role role);
    Set<User> getUsers();


    User getUserConnect(HttpServletRequest httpServletRequest);
}
