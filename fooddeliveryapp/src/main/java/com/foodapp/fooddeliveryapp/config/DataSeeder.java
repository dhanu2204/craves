package com.foodapp.fooddeliveryapp.config;

import com.foodapp.fooddeliveryapp.model.FoodItem;
import com.foodapp.fooddeliveryapp.model.Restaurant;
import com.foodapp.fooddeliveryapp.service.FoodItemService;
import com.foodapp.fooddeliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodItemService foodItemService;

    @Override
    public void run(String... args) throws Exception {
        // Check if restaurants already exist to prevent duplicate seeding
        List<Restaurant> existing = restaurantService.getAllRestaurants();
        if (existing != null && !existing.isEmpty()) {
            System.out.println("Database already seeded with Bangalore restaurants!");
            return;
        }

        System.out.println("Seeding Bangalore Restaurants...");

        // 1. Meghana Foods
        Restaurant meghana = new Restaurant();
        meghana.setName("Meghana Foods");
        meghana.setAddress("Koramangala 5th Block");
        meghana.setCity("Bangalore");
        meghana.setDescription("Famous Andhra Style Biryani");
        meghana.setImageUrl("https://example.com/meghana.jpg");
        meghana.setPhone_number("9999999991");
        meghana.setRating(4.5);
        meghana.setOpen(true);
        meghana = restaurantService.addRestaurant(meghana);

        addFoodItem(meghana, "Chicken Boneless Biryani", "Special boneless chicken biryani with authentic Andhra spices", 350.0);
        addFoodItem(meghana, "Mutton Biryani", "Rich and flavorful mutton biryani", 420.0);
        addFoodItem(meghana, "Paneer Biryani", "Spicy and delicious vegetarian biryani", 280.0);

        // 2. Truffles
        Restaurant truffles = new Restaurant();
        truffles.setName("Truffles");
        truffles.setAddress("Indiranagar 100ft Road");
        truffles.setCity("Bangalore");
        truffles.setDescription("Iconic burgers and shakes");
        truffles.setImageUrl("https://example.com/truffles.jpg");
        truffles.setPhone_number("9999999992");
        truffles.setRating(4.6);
        truffles.setOpen(true);
        truffles = restaurantService.addRestaurant(truffles);

        addFoodItem(truffles, "All American Cheese Burger", "Juicy beef patty with double cheese and secret sauce", 280.0);
        addFoodItem(truffles, "Peri Peri Fries", "Crispy fries tossed in spicy peri peri mix", 150.0);
        addFoodItem(truffles, "Ferrero Rocher Shake", "Thick chocolate shake blended with Ferrero Rocher", 220.0);

        // 3. CTR (Shri Sagar)
        Restaurant ctr = new Restaurant();
        ctr.setName("CTR (Shri Sagar)");
        ctr.setAddress("Margosa Road, Malleshwaram");
        ctr.setCity("Bangalore");
        ctr.setDescription("Legendary Benne Masala Dosa");
        ctr.setImageUrl("https://example.com/ctr.jpg");
        ctr.setPhone_number("9999999993");
        ctr.setRating(4.8);
        ctr.setOpen(true);
        ctr = restaurantService.addRestaurant(ctr);

        addFoodItem(ctr, "Benne Masala Dosa", "Crispy golden dosa roasted in pure butter", 90.0);
        addFoodItem(ctr, "Filter Coffee", "Authentic strong South Indian filter coffee", 30.0);
        addFoodItem(ctr, "Mangalore Bajji", "Soft inside, crispy outside snack", 50.0);

        // 4. The Rameshwaram Cafe
        Restaurant rameshwaram = new Restaurant();
        rameshwaram.setName("The Rameshwaram Cafe");
        rameshwaram.setAddress("Brookefield / Indiranagar");
        rameshwaram.setCity("Bangalore");
        rameshwaram.setDescription("Premium South Indian quick service");
        rameshwaram.setImageUrl("https://example.com/rameshwaram.jpg");
        rameshwaram.setPhone_number("9999999994");
        rameshwaram.setRating(4.7);
        rameshwaram.setOpen(true);
        rameshwaram = restaurantService.addRestaurant(rameshwaram);

        addFoodItem(rameshwaram, "Ghee Podi Idli", "Soft idlis soaked in ghee and spicy podi", 110.0);
        addFoodItem(rameshwaram, "Garlic Roast Dosa", "Crispy dosa with a generous spread of garlic chutney", 130.0);
        addFoodItem(rameshwaram, "Medu Vada", "Crispy deep-fried lentil donut", 50.0);

        System.out.println("Successfully seeded database with Namma Bengaluru restaurants! 🎉");
    }

    private void addFoodItem(Restaurant restaurant, String name, String desc, double price) {
        FoodItem item = new FoodItem();
        item.setName(name);
        item.setDescription(desc);
        item.setPrice(price);
        item.setRestaurant(restaurant);
        foodItemService.addFoodItem(item);
    }
}
