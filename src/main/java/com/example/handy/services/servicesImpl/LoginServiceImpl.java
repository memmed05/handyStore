package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.LoginDto;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    @Override
    public void login(LoginDto loginDto) {

    }
}
