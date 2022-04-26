package com.example.handy.controllers;

import com.example.handy.configs.Security.CustomUserDetails;
import com.example.handy.services.CategoryService;
import com.example.handy.services.CityService;
import com.example.handy.services.PostService;
import com.example.handy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/wishlist")
@RequiredArgsConstructor
public class WishedController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final UserService userService;

    @GetMapping
    public String getAllAddWish(Model model, Authentication authentication) {

        model.addAttribute("loggedUser",
                userService.findUserForLogin(getLoggedUser(authentication).getUsername()).get());
        model.addAttribute("posts", postService.getAllWished());
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("city", cityService.getAllCities());
        return "wish-list";
    }


    @GetMapping("/add/{id}")
    public String addWish(Authentication authentication, @PathVariable Integer id) {

        Integer userId = getLoggedUser(authentication).getId();

        postService.addWishList(id, userId);

        return "redirect:/dashboard";
    }

    CustomUserDetails getLoggedUser(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }

}
