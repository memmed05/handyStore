package com.example.handy.exceptions;


public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String error) {
        super(error);
    }
}
