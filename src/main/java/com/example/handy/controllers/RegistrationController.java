package com.example.handy.controllers;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user/")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping(value = "/signup")
    public String register(Model model) {
        model.addAttribute("user",new UserDto());

        return "signup";
    }

    @PostMapping(value = "/register")
    public String saveUser(UserDto userDto) throws UserAlreadyExistException {
        this.userService.register(userDto);
        return "signin";
    }
}
