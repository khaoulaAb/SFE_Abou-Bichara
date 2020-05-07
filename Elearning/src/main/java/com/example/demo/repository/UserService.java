package com.example.demo.repository;

import com.example.demo.entities.User;

public interface UserService {
    public Iterable<User> findAll();
    public User find(Long id);
    public User save(User user);
    public void delete(Long id);
}
