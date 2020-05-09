package com.security.app.jwtappdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.security.app.jwtappdemo.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationFormDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;

    public static User toUser(RegistrationFormDTO formDTO) {
        User user = new User();
        user.setUsername(formDTO.getUsername());
        user.setEmail(formDTO.getEmail());
        user.setPassword(formDTO.getPassword());
        user.setFirstName(formDTO.getFirstName());
        user.setLastName(formDTO.getLastName());

        return user;
    }
}
