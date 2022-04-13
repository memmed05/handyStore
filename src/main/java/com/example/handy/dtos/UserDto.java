package com.example.handy.dtos;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {

    @NotNull
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String matchingPassword;
}
