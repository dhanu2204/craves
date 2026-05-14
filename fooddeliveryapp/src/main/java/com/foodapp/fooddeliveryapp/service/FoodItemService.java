package com.foodapp.fooddeliveryapp.service;

import java.util.List;

import com.foodapp.fooddeliveryapp.model.FoodItem;

public interface FoodItemService {

    FoodItem addFoodItem(FoodItem foodItem);

    FoodItem updateFoodItem(FoodItem foodItem);

    FoodItem getFoodItemById(Long id);

    List<FoodItem> getFoodItemByRestaurantId(Long id);

    List<FoodItem> getAllFoodItems();

    void deleteFoodItem(Long id);
}
