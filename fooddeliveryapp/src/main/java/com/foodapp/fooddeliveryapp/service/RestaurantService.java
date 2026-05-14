package com.foodapp.fooddeliveryapp.service;

import com.foodapp.fooddeliveryapp.model.Restaurant;
import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(Long id);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantsByCity(String city);

    List<Restaurant> searchRestaurantsByName(String name);

    void deleteRestaurant(Long id);
}
