package com.foodapp.fooddeliveryapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.fooddeliveryapp.model.User;
import com.foodapp.fooddeliveryapp.model.User.Role;
import com.foodapp.fooddeliveryapp.repository.UserRepository;
import com.foodapp.fooddeliveryapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Correct way to check: Use Optional
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        // Give them a default role and save
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User LoginUser(String email, String password) {
        // Find user by email
        Optional<User> existingUserOpt = userRepository.findByEmail(email);
        
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("User not found with this email");
        }

        User existingUser = existingUserOpt.get(); // Get the user out of the Optional box

        if (existingUser.getPassword().equals(password)) {
            return existingUser;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    @Override
    public User getUserById(Long id) {
        // findById automatically returns an Optional
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        // The built-in JPA method is findAll(), not getAllUsers()
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        // Check if user exists first
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        
        userRepository.deleteById(id);
    }

}
