package com.example.handy.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String error){
        super(error);
    }
}
