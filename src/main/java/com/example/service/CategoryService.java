package com.example.service;

import com.example.dto.request.CategoryRequest;
import com.example.dto.response.CategoryResponse;
import com.example.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long categoryId);
    CategoryResponse addCategory(CategoryRequest categoryRequestDto);
    CategoryResponse getCategoryById(Long categoryId);
    List<CategoryResponse> getCategories();
    CategoryResponse deleteCategory(Long categoryId);
    CategoryResponse editCategory(Long categoryId, CategoryRequest categoryRequestDto);
}
