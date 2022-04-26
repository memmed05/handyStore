package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.services.CategoryService;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/myposts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping(value = "/{pageNum}")
    public String getPosts(@PathVariable Integer pageNum, Model model, Authentication authentication) {
        Integer id = getLoggedUser(authentication).getId();

        model.addAttribute("posts", postService.getUsersPosts(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        Page<Post> pages = postService.findPage(pageNum);

        Integer totalPages = pages.getTotalPages();
        Long totalItems = pages.getTotalElements();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalitems", totalItems);
        return "manage-post";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }

}