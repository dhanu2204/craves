package com.foodapp.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.fooddeliveryapp.model.FoodItem;
import com.foodapp.fooddeliveryapp.repository.FoodItemRepository;
import com.foodapp.fooddeliveryapp.service.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public FoodItem addFoodItem(FoodItem foodItem) {
        if (foodItem.getRestaurant() == null || foodItem.getRestaurant().getId() == null) {
            throw new RuntimeException("Food item must be associated to a restaurant");
        }
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem updateFoodItem(FoodItem foodItem) {
        FoodItem existing = foodItemRepository.getById(foodItem.getId());
        if (existing == null) {
            throw new RuntimeException("Food item not found!");
        }
        return foodItemRepository.update(foodItem);
    }

    @Override
    public void deleteFoodItem(Long id) {
        FoodItem existing = foodItemRepository.getById(id);
        if (existing == null) {
            throw new RuntimeException("Food item not found!");
        }
        foodItemRepository.deleteById(id);
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        FoodItem existing = foodItemRepository.getById(id);
        if (existing == null) {
            throw new RuntimeException("Food item not found!");
        }
        return existing;
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.getAllFoodItems();
    }

    @Override
    public List<FoodItem> getFoodItemByRestaurantId(Long id) {
        List<FoodItem> existing = foodItemRepository.getByRestaurantId(id);
        if (existing == null) {
            throw new RuntimeException("Food item not found!");
        }
        return existing;
    }
}
