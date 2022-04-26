package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.model.User;
import com.example.handy.services.UserInfoService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;
    private final UserInfoService userInfoService;


    @GetMapping("/{id}")
    public String viewUser(@PathVariable Integer id, Model model, Authentication au) {
        Integer loggedUserId = getLoggedUser(au).getId();
        User user = userService.findUserForLogin(getLoggedUser(au).getUsername()).get();


        model.addAttribute("loggedUser", user);
        model.addAttribute("user", userInfoService.viewUser(id));
        return "user";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
