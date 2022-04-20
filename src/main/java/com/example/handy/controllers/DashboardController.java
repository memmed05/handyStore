package com.example.handy.controllers;

import com.example.handy.model.Post;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final PostService postService;

    @GetMapping
    public String getPage(Model model, @RequestParam Integer pageNum){
        Page<Post> pages=postService.findPage(pageNum);
        Integer totalPages=pages.getTotalPages();
        Long totalItems=pages.getTotalElements();
        List<Post> posts=pages.getContent();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("posts",posts);

        return "dashboard";
    }

}
