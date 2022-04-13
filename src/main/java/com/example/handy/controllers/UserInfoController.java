package com.example.handy.controllers;

import com.example.handy.model.UserInfo;
import com.example.handy.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/v1/userInfo")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping (value = "/update/{id}")
    public String addUserInfos(@PathVariable Integer id, UserInfo userInfo, Model model) {
        model.addAttribute(userInfo);
        return "update-profile";
    }

}
