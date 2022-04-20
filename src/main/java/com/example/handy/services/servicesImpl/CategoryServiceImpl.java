package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.CategoryDto;
import com.example.handy.model.Category;
import com.example.handy.repositories.CategoryRepository;
import com.example.handy.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> allCategories = new ArrayList<>();
        for (Category cat : categories) {
            CategoryDto category = new CategoryDto();
            category.setId(cat.getId());
            category.setName(cat.getName());
            allCategories.add(category);
        }
        return allCategories;

    }
}
