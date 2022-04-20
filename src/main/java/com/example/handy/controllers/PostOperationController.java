package com.example.handy.controllers;

import com.example.handy.dtos.PostDto;
import com.example.handy.model.Category;
import com.example.handy.model.Post;
import com.example.handy.services.CategoryService;
import com.example.handy.services.CityService;
import com.example.handy.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostOperationController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

    @GetMapping(value = "/{id}")
    public String getAll(Model model, @PathVariable Integer id) {
        model.addAttribute("categories", categoryService.getAllCategories());
        PostDto postDto =  new PostDto();;
        if (id == 0) {
            postDto.setId(id);
        } else {
            Post post=postService.getById(id);
            postDto.setId(post.getId());
            postDto.setName(post.getName());
        }
        model.addAttribute("post", postDto);
        model.addAttribute("cities", cityService.getAllCities());
        return "a_u-post";
    }

    @PostMapping(value = "/addPost")
    public String addPost(@ModelAttribute("post") PostDto post) throws Exception {
        this.postService.addPost(post);
        return "redirect:/myposts";
    }


}
