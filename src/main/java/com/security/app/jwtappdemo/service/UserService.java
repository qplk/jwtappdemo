package com.security.app.jwtappdemo.service;

import com.security.app.jwtappdemo.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    User deleteById(Long id);
}
