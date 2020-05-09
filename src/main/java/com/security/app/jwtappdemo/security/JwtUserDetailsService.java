package com.security.app.jwtappdemo.security;

import com.security.app.jwtappdemo.model.User;
import com.security.app.jwtappdemo.security.jwt.JwtUser;
import com.security.app.jwtappdemo.security.jwt.JwtUserFactory;
import com.security.app.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            log.error("IN loadUserByUsername - username: {} does not exist", username);
            throw new UsernameNotFoundException("User with username: " + user + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.createJwtUser(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
