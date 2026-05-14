package com.foodapp.fooddeliveryapp.service;

import com.foodapp.fooddeliveryapp.model.Cart;

public interface CartService {
    Cart getCartByUserId(Long userId);
    Cart addItemToCart(Long userId, Long foodItemId, int quantity);
    Cart updateItemQuantity(Long cartItemId, int quantity);
    Cart removeItemFromCart(Long cartItemId);
    void clearCart(Long cartId);
}
