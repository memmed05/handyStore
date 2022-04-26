package com.example.handy.repositories;

import com.example.handy.model.Post;
import com.example.handy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Integer id);

    List<Post> findByUser(User user);
}
