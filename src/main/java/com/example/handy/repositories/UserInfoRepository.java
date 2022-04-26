package com.example.handy.repositories;

import com.example.handy.model.User;
import com.example.handy.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByUser(User user);
}
