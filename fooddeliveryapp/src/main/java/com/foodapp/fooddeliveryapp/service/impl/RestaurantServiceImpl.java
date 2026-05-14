package com.foodapp.fooddeliveryapp.service.impl;

import com.foodapp.fooddeliveryapp.model.Restaurant;
import com.foodapp.fooddeliveryapp.repository.RestaurantRepository;
import com.foodapp.fooddeliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        // By default, a new restaurant is open and has 0.0 rating
        restaurant.setOpen(true);
        restaurant.setRating(0.0);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant existing = restaurantRepository.getRestaurantById(restaurant.getId());
        if (existing == null) {
            throw new RuntimeException("Restaurant not found!");
        }
        return restaurantRepository.update(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        Restaurant existing = restaurantRepository.getRestaurantById(id);
        if (existing == null) {
            throw new RuntimeException("Restaurant not found!");
        }
        return existing;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    @Override
    public List<Restaurant> getRestaurantsByCity(String city) {
        return restaurantRepository.getByCity(city);
    }

    @Override
    public List<Restaurant> searchRestaurantsByName(String name) {
        return restaurantRepository.getByName(name);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant existing = restaurantRepository.getRestaurantById(id);
        if (existing == null) {
            throw new RuntimeException("Restaurant not found, cannot delete.");
        }
        restaurantRepository.deleteById(id);
    }
}
