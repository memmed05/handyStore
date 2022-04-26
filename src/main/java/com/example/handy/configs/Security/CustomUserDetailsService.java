package com.example.handy.configs.Security;

import com.example.handy.model.User;
import com.example.handy.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;


@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    //Converting from Application User entity to conventional UserDetail
    public static UserDetails userToUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles()
                .build();
    }

    //Converting from Application User entity to Application UserrDetail
    public static UserDetails userToUserrDetails(User user) {
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(r -> "ROLE_" + r)
                        .map(r -> (GrantedAuthority) () -> r.toString()).collect(Collectors.toSet()),
                true,
                true,
                true,
                true
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findUserForLogin(email)
                .map(CustomUserDetailsService::userToUserrDetails).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
