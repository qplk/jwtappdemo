package com.security.app.jwtappdemo.controller;

import com.security.app.jwtappdemo.entity.RegistrationFormDTO;
import com.security.app.jwtappdemo.exception.RegistrationException;
import com.security.app.jwtappdemo.model.User;
import com.security.app.jwtappdemo.service.UserService;
import com.security.app.jwtappdemo.utility.RegistrationUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void registerClient(@RequestBody RegistrationFormDTO registrationForm) {

        if (!RegistrationUtility.validateRegistrationForm(registrationForm)) {
            log.error("Can not register client");
            throw new RegistrationException();
        }

        User user = RegistrationFormDTO.toUser(registrationForm);
        userService.register(user);
    }
}
