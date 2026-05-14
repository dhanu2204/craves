package com.foodapp.fooddeliveryapp.service;

import java.util.List;
import com.foodapp.fooddeliveryapp.model.User;

public interface UserService {

    User registerUser(User user);
    User LoginUser(String email, String password);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
