package com.example.BalanceTrackerApp.DTO;

import java.time.LocalDate;

public class IncomeDTO {
    private Long id;
    private String title;
    private String amount;
    private String date;  // Keep as String to avoid JSON parsing issues
    private String description;
    private String category;
    private Long userId;  // ✅ Use userId instead of User object

    // Constructors
    public IncomeDTO() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public IncomeDTO(Long id, String title, String amount, String date, String description, String category, Long userId) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }



    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
