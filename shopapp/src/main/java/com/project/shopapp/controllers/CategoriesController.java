package com.project.shopapp.controllers;

import com.project.shopapp.Services.CategoryService;
import com.project.shopapp.dto.request.ApiResponse;
import com.project.shopapp.dto.request.CategoryCreationRequest;
import com.project.shopapp.dto.response.CategoryResponse;
import com.project.shopapp.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreationRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories(){
        return ApiResponse.<List<Category>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable("id") long id){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategoryById(id))
                .build();
    }

    @PostMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable("id") long id, @RequestBody CategoryCreationRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable("id") long id){
        return ApiResponse.<Void>builder().build();
    }
}
