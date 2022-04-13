package com.example.handy.controllers;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user/")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping(value = "/signup")
    public String register(UserDto userDto, Model model) {
        model.addAttribute("user", userDto);
        return "signup";
    }

    @PostMapping(value = "/register")
    public String saveUser(@ModelAttribute("user") UserDto userDto) throws UserAlreadyExistException {
        this.userService.register(userDto);
        return "redirect:/signup";
    }
}
