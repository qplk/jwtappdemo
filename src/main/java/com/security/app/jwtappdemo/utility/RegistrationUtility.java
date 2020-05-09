package com.security.app.jwtappdemo.utility;

import com.security.app.jwtappdemo.entity.RegistrationFormDTO;
import com.security.app.jwtappdemo.exception.RegistrationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistrationUtility {

    public static boolean validateRegistrationForm(RegistrationFormDTO form) {

        if (form == null) {
            log.error("Registration form is null");
            return false;
        }

        if (form.getEmail() == null) {
            log.error("Email can not be empty");
            return false;
        }

        if (form.getUsername() == null) {
            log.error("Username can not be empty");
            return false;
        }

        if (form.getPassword() == null || (form.getPassword().length() < 8)) {
            log.error("Password is not valid, please create another");
            return false;
        }

        return true;
    }
}
