package com.foodapp.fooddeliveryapp.service.impl;

import com.foodapp.fooddeliveryapp.model.Cart;
import com.foodapp.fooddeliveryapp.model.CartItem;
import com.foodapp.fooddeliveryapp.model.FoodItem;
import com.foodapp.fooddeliveryapp.model.User;
import com.foodapp.fooddeliveryapp.repository.CartItemRepository;
import com.foodapp.fooddeliveryapp.repository.CartRepository;
import com.foodapp.fooddeliveryapp.repository.FoodItemRepository;
import com.foodapp.fooddeliveryapp.repository.UserRepository;
import com.foodapp.fooddeliveryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.getByUserId(userId);

        // Business Rule: If a user doesn't have a cart yet, create an empty one for
        // them!
        if (cart == null) {
            User user = userRepository.getUserById(userId);
            if (user == null) {
                throw new RuntimeException("User not found!");
            }
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(0.0);
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart addItemToCart(Long userId, Long foodItemId, int quantity) {
        // 1. Get the user's cart (or create it if it doesn't exist)
        Cart cart = getCartByUserId(userId);

        // 2. Find the food item they want to add
        FoodItem foodItem = foodItemRepository.getById(foodItemId);
        if (foodItem == null) {
            throw new RuntimeException("Food item not found!");
        }

        // 3. Check if this item is ALREADY in their cart
        CartItem existingCartItem = null;
        if (cart.getItems() != null) {
            for (CartItem item : cart.getItems()) {
                if (item.getFoodItem().getId().equals(foodItemId)) {
                    existingCartItem = item;
                    break;
                }
            }
        }

        // 4. If it's already there, just increase the quantity. Otherwise, create a new
        // CartItem.
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            existingCartItem.setPrice(foodItem.getPrice() * existingCartItem.getQuantity());
            cartItemRepository.update(existingCartItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setFoodItem(foodItem);
            newItem.setQuantity(quantity);
            newItem.setPrice(foodItem.getPrice() * quantity);
            cartItemRepository.save(newItem);
        }

        // 5. Recalculate the Cart's total price
        updateCartTotal(cart);
        return cartRepository.update(cart);
    }

    @Override
    public Cart updateItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.getById(cartItemId);
        if (cartItem == null) {
            throw new RuntimeException("Cart item not found!");
        }

        if (quantity <= 0) {
            return removeItemFromCart(cartItemId);
        }

        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getFoodItem().getPrice() * quantity);
        cartItemRepository.update(cartItem);

        Cart cart = cartItem.getCart();
        updateCartTotal(cart);
        return cartRepository.update(cart);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.getById(cartItemId);
        if (cartItem == null) {
            throw new RuntimeException("Cart item not found!");
        }

        Cart cart = cartItem.getCart();
        cartItemRepository.deleteById(cartItemId);

        updateCartTotal(cart);
        return cartRepository.update(cart);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.getById(cartId);
        if (cart != null && cart.getItems() != null) {
            for (CartItem item : cart.getItems()) {
                cartItemRepository.deleteById(item.getId());
            }
            cart.setTotalPrice(0.0);
            cartRepository.update(cart);
        }
    }

    // Helper method to recalculate total price
    private void updateCartTotal(Cart cart) {
        double total = 0;
        // Fetch fresh items from database to ensure we have the latest
        var items = cartItemRepository.getByCartId(cart.getId());
        for (CartItem item : items) {
            total += item.getPrice();
        }
        cart.setTotalPrice(total);
    }
}
