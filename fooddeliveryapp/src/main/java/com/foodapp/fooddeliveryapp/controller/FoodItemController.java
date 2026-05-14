package com.foodapp.fooddeliveryapp.controller;

import com.foodapp.fooddeliveryapp.model.FoodItem;
import com.foodapp.fooddeliveryapp.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fooditems")
@CrossOrigin(origins = "*")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<?> addFoodItem(@RequestBody FoodItem foodItem) {
        try {
            FoodItem saved = foodItemService.addFoodItem(foodItem);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        try {
            foodItem.setId(id);
            FoodItem updated = foodItemService.updateFoodItem(foodItem);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodItemById(@PathVariable Long id) {
        try {
            FoodItem foodItem = foodItemService.getFoodItemById(id);
            return new ResponseEntity<>(foodItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return new ResponseEntity<>(foodItemService.getAllFoodItems(), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItem>> getFoodItemsByRestaurantId(@PathVariable Long restaurantId) {
        return new ResponseEntity<>(foodItemService.getFoodItemByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable Long id) {
        try {
            foodItemService.deleteFoodItem(id);
            return new ResponseEntity<>("Food item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
