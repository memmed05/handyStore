package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.dtos.UserInfoDto;
import com.example.handy.exceptions.EmptyFillInfoException;
import com.example.handy.model.User;
import com.example.handy.model.UserInfo;
import com.example.handy.services.CityService;
import com.example.handy.services.UserInfoService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(name = "/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;
    private final UserInfoService userInfoService;
    private final CityService cityService;

    @GetMapping
    public String handle_get(Model model, Authentication au) {

        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("loggedUser", userService.findUserForLogin(getLoggedUser(au).getUsername()));

        return "anket";
    }

    @PostMapping
    public RedirectView handle_post(UserInfoDto userInfoDto,
                                    Authentication au) throws Exception {
        Optional<User> user = userService.findUserForLogin(getLoggedUser(au).getUsername());
        userInfoService.fillInfo(userInfoDto, user.get().getId());
        return new RedirectView("dashboard");
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }


}
