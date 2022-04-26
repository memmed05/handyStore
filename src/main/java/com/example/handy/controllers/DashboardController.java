package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.model.Category;
import com.example.handy.model.Post;
import com.example.handy.services.CategoryService;
import com.example.handy.services.PostService;
import com.example.handy.services.UserInfoService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final UserInfoService userInfoService;
    private final UserService userService;

    @GetMapping(value = "/{pageNum}")
    public String getPage(Model model, @PathVariable Integer pageNum, Authentication au) {
        Page<Post> pages = postService.findPage(pageNum);
        Integer totalPages = pages.getTotalPages();
        Long totalItems = pages.getTotalElements();
        List<Post> posts = pages.getContent();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalitems", totalItems);
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUser", userService.findUserForLogin(getLoggedUser(au).getUsername()).get());

        return "dashboard";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
