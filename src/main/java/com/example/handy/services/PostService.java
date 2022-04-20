package com.example.handy.services;

import com.example.handy.dtos.PostDto;
import com.example.handy.model.Post;
import com.example.handy.model.WishList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post addPost(PostDto postDto) throws Exception;

    void deletePost(Integer id);

    PostDto updatePost(Integer id,PostDto postDto);

    Page<Post> findPage(Integer currentPage);

    void addWishList(PostDto postDto);

    Post getById(Integer id);
}
