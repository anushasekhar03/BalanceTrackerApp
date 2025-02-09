package com.example.BalanceTrackerApp.DTO;

import java.time.LocalDate;

public class ExpenseDTO {

    private Long id;  // Leave this as null when creating new expense (set only for updates)
    private String title;
    private String amount;
    private String date;  // Use String for easier serialization/deserialization
    private String description;
    private String category;
    private Long userId;  // Use userId to link to the User entity

    // Constructors, getters, and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;  // Only set this for updates, not for new entries
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
