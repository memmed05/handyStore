package com.example.handy.exceptions;

public class WrongEmailException extends Exception{
    public WrongEmailException(String error){
        super(error);
    }
}
