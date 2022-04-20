package com.example.handy.dtos;

import com.example.handy.model.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class PostDto {

    private Integer id;
    private String name;
    private String city;
    private String expiryDate;
    private Category category;
    private MultipartFile image;
}
