package com.foodapp.fooddeliveryapp.service.impl;

import com.foodapp.fooddeliveryapp.model.*;
import com.foodapp.fooddeliveryapp.repository.*;
import com.foodapp.fooddeliveryapp.service.CartService;
import com.foodapp.fooddeliveryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CartService cartService; // We reuse the CartService we just made!

    @Override
    public Order placeOrder(Long userId, Long restaurantId, String deliveryAddress, String paymentMethod) {

        // 1. Get the User and Restaurant
        User user = userRepository.getUserById(userId);
        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);

        if (user == null || restaurant == null) {
            throw new RuntimeException("Invalid User or Restaurant!");
        }

        // 2. Get the user's cart
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cannot place order. Cart is empty!");
        }

        // 3. Create the actual Order
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryAddress(deliveryAddress);
        order.setStatus(Order.OrderStatus.PENDING);
        order.setTotalPrice(cart.getTotalPrice());

        // Save the order FIRST so we get an ID
        order = orderRepository.save(order);

        // 4. Convert CartItems into OrderItems
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFoodItem(cartItem.getFoodItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());

            orderItemRepository.save(orderItem); // Save each item to the database
        }

        // 5. Create a Payment Record
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentMethod(Payment.PaymentMethod.valueOf(paymentMethod.toUpperCase()));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentstatus(Payment.PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        // 6. Clear the Cart now that the order is placed!
        cartService.clearCart(cart.getId());

        return order;
    }

    @Override
    public Order updateOrderStatus(Long orderId, Order.OrderStatus newStatus) {
        Order order = orderRepository.getById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found!");
        }
        order.setStatus(newStatus);
        return orderRepository.update(order);
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.getById(id);
        if (order == null) {
            throw new RuntimeException("Order not found!");
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.getByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByRestaurantId(Long restaurantId) {
        return orderRepository.getByRestaurantId(restaurantId);
    }
}
