package com.example.handy.services;

import com.example.handy.dtos.CategoryDto;
import com.example.handy.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

}
