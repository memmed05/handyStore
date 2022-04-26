package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.dtos.UserInfoDto;
import com.example.handy.model.User;
import com.example.handy.services.CityService;
import com.example.handy.services.UserInfoService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/update")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserService userService;
    private final CityService cityService;
    private final UserInfoService userInfoService;


    @GetMapping
    public String updateUser(Model model, Authentication au) {
        model.addAttribute("loggedUser", userService.findUserForLogin(getLoggedUser(au).getUsername()).get());
        model.addAttribute("cities", cityService.getAllCities());
        return "update-profile";
    }


    @PostMapping()
    public String handle_post(UserInfoDto userInfoDto,
                              Model model,
                              Authentication au) throws Exception {
        User user=userService.getById(getLoggedUser(au).getId());
        userInfoService.updateUser(userInfoDto, user.getId());

        model.addAttribute("process", "profileupdated");

        return "redirect:/dashboard/1";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
