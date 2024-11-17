package com.project.shopapp.controllers;

import com.project.shopapp.Services.CategoryService;
import com.project.shopapp.dto.request.CategoryCreationRequest;
import com.project.shopapp.dto.response.CategoryResponse;
import com.project.shopapp.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryCreationRequest request){
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable("id") long id, @RequestBody CategoryCreationRequest request){
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
    }
}
