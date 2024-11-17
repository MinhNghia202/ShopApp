package com.project.shopapp.Services;

import com.project.shopapp.Repositories.CategoryRepository;
import com.project.shopapp.dto.request.CategoryCreationRequest;
import com.project.shopapp.dto.response.CategoryResponse;
import com.project.shopapp.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public CategoryResponse getCategoryById(long id) {
        Category category = getById(id);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private Category getById(long id){
        return categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found!"));
    }

    @Override
    public CategoryResponse updateCategory(long id, CategoryCreationRequest category) {
        Category existingCategory = getById(id);
        existingCategory.setName(category.getName());
        categoryRepository.save(existingCategory);
        return CategoryResponse.builder()
                .id(existingCategory.getId())
                .name(existingCategory.getName())
                .build();
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
