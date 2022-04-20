package com.example.handy.services;

import com.example.handy.dtos.LoginDto;
import com.example.handy.exceptions.WrongEmailException;
import com.example.handy.exceptions.WrongPasswordException;
import com.example.handy.model.User;

public interface LoginService {

    User login(LoginDto loginDto) throws WrongEmailException, WrongPasswordException;
}
