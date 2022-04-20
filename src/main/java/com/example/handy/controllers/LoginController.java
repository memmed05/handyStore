package com.example.handy.controllers;

import com.example.handy.configs.Security.token.ConfirmationToken;
import com.example.handy.configs.Security.token.ConfirmationTokenService;
import com.example.handy.dtos.LoginDto;
import com.example.handy.exceptions.WrongEmailException;
import com.example.handy.exceptions.WrongPasswordException;
import com.example.handy.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/user/")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping(value = "/signin")
    public String signIn(Model model,LoginDto loginDto) throws WrongEmailException , WrongPasswordException {
        model.addAttribute("login",loginDto);
        Optional<ConfirmationToken> confirmationToken=
                confirmationTokenService.getConfirmationToken(loginDto.getToken());

        if (confirmationToken.isPresent()){
            return "dashboard";
        }

        return "signin";
    }


}
