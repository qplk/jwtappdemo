package com.security.app.jwtappdemo.entity;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String username;
    private String password;
}
