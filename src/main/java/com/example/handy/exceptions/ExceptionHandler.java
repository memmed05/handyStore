package com.example.handy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(
            UserAlreadyExistException.class)
    public String handleException(UserAlreadyExistException e, Model model) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
        model.addAttribute("error", errorDetails.getMessage());
        return "redirect:/user/signup";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(
            WrongEmailException.class)
    public String handleException(WrongEmailException e, Model model) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
        model.addAttribute("error");
        return "redirect:/user/signin";
    }

}
