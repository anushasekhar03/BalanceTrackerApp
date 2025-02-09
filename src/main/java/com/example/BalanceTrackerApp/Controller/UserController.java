package com.example.BalanceTrackerApp.Controller;

import com.example.BalanceTrackerApp.Entity.User;
import com.example.BalanceTrackerApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);  // Will return User with related Income/Expense
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();  // Will return all users with related Income/Expense
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);  // Will delete user
    }
}
