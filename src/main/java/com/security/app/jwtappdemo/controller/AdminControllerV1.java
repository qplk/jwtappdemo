package com.security.app.jwtappdemo.controller;

import com.security.app.jwtappdemo.entity.UserDTO;
import com.security.app.jwtappdemo.model.User;
import com.security.app.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/")
public class AdminControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            log.debug("Can not find user by id: {}", id);
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO userDTO = UserDTO.fromUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
