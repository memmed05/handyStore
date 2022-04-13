package com.example.handy.services;

import com.example.handy.dtos.UserDto;
import com.example.handy.exceptions.UserAlreadyExistException;
import com.example.handy.model.User;

public interface UserService {
    User register(UserDto userDto) throws UserAlreadyExistException;
}
