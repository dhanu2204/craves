package com.foodapp.fooddeliveryapp.service;

import com.foodapp.fooddeliveryapp.model.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, Long restaurantId, String deliveryAddress, String paymentMethod);
    Order updateOrderStatus(Long orderId, Order.OrderStatus newStatus);
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getOrdersByRestaurantId(Long restaurantId);
}
