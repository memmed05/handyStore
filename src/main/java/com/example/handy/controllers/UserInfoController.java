package com.example.handy.controllers;

import com.example.handy.model.UserInfo;
import com.example.handy.services.CityService;
import com.example.handy.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(name = "/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CityService cityService;

    @GetMapping (value = "/update")
    public String setUserInfos(UserInfo userInfo, Model model) {
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("cities",this.cityService.getAllCities());
        return "update-profile";
    }

    @PostMapping(value = "/save")
    public String saveUserInfos(@PathVariable Integer id, UserInfo userInfo){
        this.userInfoService.setUserInfo(id,userInfo);
        return "redirect:/info/update";
    }


}
