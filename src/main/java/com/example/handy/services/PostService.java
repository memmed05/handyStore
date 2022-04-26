package com.example.handy.services;

import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.model.User;
import com.example.handy.model.WishList;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAllPosts();

    Post addPost(PostDto postDto, Optional<User> user) throws Exception;

    void deletePost(Integer id);

    Post updatePost(Integer id, PostDto postDto) throws Exception;

    Page<Post> findPage(Integer currentPage);

    WishList addWishList(Integer id, Integer userId);

    List<WishList> getAllWished();

    Post getById(Integer id);

    List<Post> getUsersPosts(Integer id);

}
