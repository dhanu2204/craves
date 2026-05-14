package com.foodapp.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.fooddeliveryapp.model.Category;
import com.foodapp.fooddeliveryapp.repository.CategoryRepository;
import com.foodapp.fooddeliveryapp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        Category existing = categoryRepository.getByName(category.getName());
        if (existing != null) {
            throw new RuntimeException("Category already exists with name " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category existing = categoryRepository.getById(category.getId());
        if (existing == null) {
            throw new RuntimeException("Category not found");
        }
        return categoryRepository.update(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category existing = categoryRepository.getById(id);
        if (existing == null) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        Category existing = categoryRepository.getById(id);
        if (existing == null) {
            throw new RuntimeException("Category not found");
        }
        return existing;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

}
