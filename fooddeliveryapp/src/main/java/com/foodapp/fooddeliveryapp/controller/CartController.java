package com.foodapp.fooddeliveryapp.controller;

import com.foodapp.fooddeliveryapp.model.Cart;
import com.foodapp.fooddeliveryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestParam Long userId, @RequestParam Long foodItemId, @RequestParam int quantity) {
        try {
            Cart cart = cartService.addItemToCart(userId, foodItemId, quantity);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateItem/{cartItemId}")
    public ResponseEntity<?> updateItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        try {
            Cart cart = cartService.updateItemQuantity(cartItemId, quantity);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeItem/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartItemId) {
        try {
            Cart cart = cartService.removeItemFromCart(cartItemId);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<?> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return new ResponseEntity<>("Cart cleared successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
