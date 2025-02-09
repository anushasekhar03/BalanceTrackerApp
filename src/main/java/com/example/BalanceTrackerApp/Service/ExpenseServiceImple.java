package com.example.BalanceTrackerApp.Service;

import com.example.BalanceTrackerApp.DTO.ExpenseDTO;
import com.example.BalanceTrackerApp.Entity.Expense;
import com.example.BalanceTrackerApp.Entity.User;
import com.example.BalanceTrackerApp.Repository.ExpenseRepo;
import com.example.BalanceTrackerApp.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImple implements ExpenseService {

    @Autowired
    private ExpenseRepo repo;

    @Autowired
    private UserRepo userRepo;

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        // Set all fields except the ID for new expenses
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(LocalDate.parse(expenseDTO.getDate()));  // Convert date String to LocalDate
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        // Fetch the User by userId and set it to the Expense
        User user = userRepo.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + expenseDTO.getUserId()));
        expense.setUser(user);

        return repo.save(expense);  // Let Hibernate automatically handle the ID
    }

    @Override
    public Expense addExpense(ExpenseDTO expenseDto) {
        Expense expense = new Expense();
        return saveOrUpdateExpense(expense, expenseDto);  // No ID set here
    }

    @Override
    public List<Expense> getAllExpense() {
        return repo.findAll();
    }

    @Override
    public Expense getById(Long id) {
        Optional<Expense> item = repo.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }

    @Override
    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> item = repo.findById(id);
        if (item.isPresent()) {
            return saveOrUpdateExpense(item.get(), expenseDTO);
        } else {
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }

    @Override
    public void deleteByid(Long id) {
        Optional<Expense> del = repo.findById(id);
        if (del.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }
}
