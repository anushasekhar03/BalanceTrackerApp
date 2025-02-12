package com.example.BalanceTrackerApp.DTO;

import com.example.BalanceTrackerApp.Entity.Expense;
import com.example.BalanceTrackerApp.Entity.Income;
import com.example.BalanceTrackerApp.Entity.User;


import java.util.List;

public class GraphDTO {

    private List<Income> incomeList;
    private List<Expense> expensesList;
    private List<User> usersList;  // Optional: if you want to include users as well

    // Getters and setters
    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Expense> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expense> expensesList) {
        this.expensesList = expensesList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
