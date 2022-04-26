package com.example.handy.controllers;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;
import com.example.handy.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

@AllArgsConstructor
@Controller
@RequestMapping("/signup")
public class RegistrationController {

    private final UserService userService;

    // http://localhost:5000/signup
    @GetMapping
    public String handle_get(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }
        return "signup";
    }

    @PostMapping
    public String handle_post(UserDto form) throws UserAlreadyExistException {
        userService.register(form);
        return "/signin";
    }
}
