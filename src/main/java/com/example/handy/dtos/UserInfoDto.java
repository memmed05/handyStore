package com.example.handy.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserInfoDto {

    private Integer id;
    private String name;
    private String surname;
    private String city;
    private String number;
    private MultipartFile file;
}

