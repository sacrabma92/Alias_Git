package com.springboot.blog.service;

import com.springboot.blog.dto.CategoryDTO;
import com.springboot.blog.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    void deleteCategory(Long id);
}
