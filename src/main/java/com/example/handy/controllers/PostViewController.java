package com.example.handy.controllers;

import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.services.CategoryService;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/myposts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;

    @GetMapping
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());

        return "manage-post";
    }

    @GetMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable Integer id){
        this.postService.deletePost(id);
        return "redirect:/myposts";
    }


}