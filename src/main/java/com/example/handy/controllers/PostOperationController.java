package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.model.User;
import com.example.handy.services.CategoryService;
import com.example.handy.services.CityService;
import com.example.handy.services.PostService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Controller
@RequestMapping(value = "/mypost")
@RequiredArgsConstructor
public class PostOperationController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public String getAll(Model model, @PathVariable Integer id, Authentication au) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUser", userService.findUserForLogin(getLoggedUser(au).getUsername()));
        PostDto postDto = new PostDto();

        if (id == 0) {
            postDto.setId(id);
        } else {
            Post post = postService.getById(id);
            postDto.setId(post.getId());
            postDto.setName(post.getName());
        }
        model.addAttribute("post", postDto);
        model.addAttribute("cities", cityService.getAllCities());
        return "a_u-post";
    }

    @PostMapping(value = "/addPost")
    public String addPost(@ModelAttribute("post") PostDto post, Authentication authentication) throws Exception {
        Optional<User> user = userService.findUserForLogin(getLoggedUser(authentication).getUsername());
        this.postService.addPost(post, user);
        return "redirect:/myposts/1";
    }

    @GetMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable Integer id) {
        this.postService.deletePost(id);
        return "redirect:/myposts/1";
    }

    @PostMapping(value = "/update/{id}")
    public String updatePost(@PathVariable Integer id, PostDto postDto) throws Exception {
        this.postService.updatePost(id, postDto);
        return "redirect:/myposts/1";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }


}
