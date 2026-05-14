package com.foodapp.fooddeliveryapp.service;

import java.util.List;

import com.foodapp.fooddeliveryapp.model.Category;

public interface CategoryService {
    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long id);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();
}
