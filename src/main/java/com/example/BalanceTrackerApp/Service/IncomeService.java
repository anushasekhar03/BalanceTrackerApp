package com.example.BalanceTrackerApp.Service;

import com.example.BalanceTrackerApp.DTO.IncomeDTO;
import com.example.BalanceTrackerApp.Entity.Income;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);   // Create a new income
    List<Income> getallIncome();              // Get all incomes
    Income getById(Long id);                  // Get income by ID
    Income updateIncome(Long id, IncomeDTO incomeDTO);  // Update an income
    void deleteIncome(Long id);               // Delete an income
}

