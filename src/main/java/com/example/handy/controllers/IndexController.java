package com.example.handy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/index")
public class IndexController {
    @GetMapping
    public String index() {
        return "index";
    }

}
