package com.example.BalanceTrackerApp.DTO;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private List<IncomeDTO> incomes;
    private List<ExpenseDTO> expenses;


    public UserDTO() {}

    public UserDTO(Long id, String name, String email, List<IncomeDTO> incomes, List<ExpenseDTO> expenses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<IncomeDTO> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeDTO> incomes) {
        this.incomes = incomes;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }
}

