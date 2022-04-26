package com.example.handy.dtos;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {

    private Integer id;

    private String email;

    private String password;

    private String matchingPassword;


}
