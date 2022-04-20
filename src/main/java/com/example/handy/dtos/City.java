package com.example.handy.dtos;

import lombok.Data;

@Data
public class City {

    private Integer id;
    private String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
