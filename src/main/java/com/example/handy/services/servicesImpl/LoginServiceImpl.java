package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.LoginDto;
import com.example.handy.exceptions.WrongEmailException;
import com.example.handy.exceptions.WrongPasswordException;
import com.example.handy.model.User;
import com.example.handy.repositories.UserRepository;
import com.example.handy.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public User login(LoginDto loginDto) throws WrongEmailException, WrongPasswordException {
        User user=userRepository.findByEmail(loginDto.getEmail());
        if (user.getEmail()==null){
            throw new WrongEmailException("Wrong email");
        }
        if (user.getPassword()!=loginDto.getPassword()){
            throw new WrongPasswordException("Wrong Password");
        }

        return user;
    }
}
