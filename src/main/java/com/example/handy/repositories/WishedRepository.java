package com.example.handy.repositories;

import com.example.handy.model.Post;
import com.example.handy.model.User;
import com.example.handy.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishedRepository extends JpaRepository<WishList, Integer> {
    WishList findByUser(User user);
    WishList findByPost(Post post);
}
