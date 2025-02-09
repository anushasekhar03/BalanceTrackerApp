package com.example.BalanceTrackerApp.Service;

import com.example.BalanceTrackerApp.DTO.ExpenseDTO;
import com.example.BalanceTrackerApp.Entity.Expense;

import java.util.List;

public interface ExpenseService {

    public Expense addExpense(ExpenseDTO expenseDto);
    public List<Expense> getAllExpense();
    public Expense getById(Long id);
    public Expense updateExpense(Long id, ExpenseDTO expenseDTO);
    public void deleteByid(Long id);
}
