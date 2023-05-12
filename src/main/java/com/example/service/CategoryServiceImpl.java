package com.example.service;

import com.example.dto.Mapper;
import com.example.dto.request.CategoryRequest;
import com.example.dto.response.CategoryResponse;
import com.example.model.Category;
import com.example.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("could not find category with id: " + categoryId));
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        categoryRepository.save(category);
        return Mapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = getCategory(categoryId);
        return Mapper.categoryToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return Mapper.categoriesToCategoryResponseList(categories);
    }

    @Override
    public CategoryResponse deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return Mapper.categoryToCategoryResponse(category);
    }

    @Transactional
    @Override
    public CategoryResponse editCategory(Long categoryId, CategoryRequest categoryRequestDto) {
        Category categoryToEdit = getCategory(categoryId);
        categoryToEdit.setName(categoryRequestDto.getName());
        return Mapper.categoryToCategoryResponse(categoryToEdit);
    }
}
