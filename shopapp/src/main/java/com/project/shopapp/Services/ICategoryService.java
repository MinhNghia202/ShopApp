package com.project.shopapp.Services;

import com.project.shopapp.dto.request.CategoryCreationRequest;
import com.project.shopapp.dto.response.CategoryResponse;
import com.project.shopapp.models.Category;

import java.util.List;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryCreationRequest request);
    CategoryResponse getCategoryById(long id);
    List<Category> getAllCategories();
    CategoryResponse updateCategory(long id, CategoryCreationRequest category);
    void deleteCategory(long id);
}
