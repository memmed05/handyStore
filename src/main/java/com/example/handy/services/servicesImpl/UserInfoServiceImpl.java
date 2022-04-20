package com.example.handy.services.servicesImpl;

import com.example.handy.model.UserInfo;
import com.example.handy.repositories.UserInfoRepository;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;


    @Override
    public UserInfo setUserInfo(Integer id, UserInfo userInfo) {
        userInfo.setUser(userRepository.getById(id));
        return this.userInfoRepository.save(userInfo);
    }
}
