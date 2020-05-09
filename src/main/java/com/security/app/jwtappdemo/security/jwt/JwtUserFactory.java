package com.security.app.jwtappdemo.security.jwt;

import com.security.app.jwtappdemo.model.Role;
import com.security.app.jwtappdemo.model.Status;
import com.security.app.jwtappdemo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser createJwtUser(User user) {
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                Status.ACTIVE.equals(user.getStatus()),
                user.getUpdatedAt(),
                convertRolesToAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    public static List<GrantedAuthority> convertRolesToAuthorities(List<Role> listRoles) {
        return listRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
