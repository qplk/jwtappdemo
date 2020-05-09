package com.security.app.jwtappdemo.service.Impl;

import com.security.app.jwtappdemo.model.Role;
import com.security.app.jwtappdemo.model.Status;
import com.security.app.jwtappdemo.model.User;
import com.security.app.jwtappdemo.repository.RoleRepository;
import com.security.app.jwtappdemo.repository.UserRepository;
import com.security.app.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleList);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register, user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();

        log.info("IN getAll - {} users found", result.size());

        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);

        log.info("IN findByUsername - user: {} found by username: {}", result, username);

        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.getOne(id);

        if (result == null) {
            log.warn("IN findById - user with ID: {} not found", id);
            return null;
        }
        log.info("IN findById - user: {} found by ID: {}", result, id);

        return result;
    }

    @Override
    public User deleteById(Long id) {
        userRepository.deleteById(id);

        log.info("IN deleteById - user with id: {} deleted", id);

        return null;
    }
}
