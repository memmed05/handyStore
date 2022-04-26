package com.example.handy.services;

import com.example.handy.dtos.UserInfoDto;
import com.example.handy.exceptions.EmptyFillInfoException;
import com.example.handy.model.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserInfoService {


    void fillInfo(UserInfoDto userInfoDto, Integer id) throws Exception;

    void updateUser(UserInfoDto userInfoDto, Integer id) throws Exception;

    UserInfo viewUser(Integer id);
}
