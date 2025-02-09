package com.example.BalanceTrackerApp.Service;

import com.example.BalanceTrackerApp.Entity.User;
import com.example.BalanceTrackerApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    // Save new User
    public User saveUser(User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    // Get a User by ID
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        // Ensure to eagerly load related Income and Expense data
        user.ifPresent(u -> {
            u.getIncomes(); // This will load the incomes
            u.getExpenses(); // This will load the expenses
        });
        return user;
    }

    // Get all Users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.getIncomes();  // Ensure related incomes are loaded
            user.getExpenses();  // Ensure related expenses are loaded
        });
        return users;
    }

    // Delete User by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
