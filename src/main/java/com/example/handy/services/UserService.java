package com.example.handy.services;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.exceptions.WrongPasswordException;
import com.example.handy.model.User;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Optional;

public interface UserService {
    User register(UserDto userDto) throws UserAlreadyExistException;

    void login(String email, String password) throws WrongPasswordException;

    Optional<User> findUserForLogin(String email);
    User getById(Integer id);
}
