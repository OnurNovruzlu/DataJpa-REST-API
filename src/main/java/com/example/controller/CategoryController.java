package com.example.controller;

import com.example.dto.request.CategoryRequest;
import com.example.dto.response.CategoryResponse;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody final CategoryRequest request) {
        return categoryService.addCategory(request);
    }

    @GetMapping("/get/{id}")
    public CategoryResponse getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/all")
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse editCategory(@RequestBody CategoryRequest request,
                                         @PathVariable("id") Long id) {
        return categoryService.editCategory(id, request);
    }


}






























