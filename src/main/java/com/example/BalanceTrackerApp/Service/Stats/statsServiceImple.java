package com.example.BalanceTrackerApp.Service.Stats;

import com.example.BalanceTrackerApp.DTO.GraphDTO;
import com.example.BalanceTrackerApp.DTO.StatsDTO;
import com.example.BalanceTrackerApp.Entity.Expense;
import com.example.BalanceTrackerApp.Entity.Income;
import com.example.BalanceTrackerApp.Entity.User;
import com.example.BalanceTrackerApp.Repository.ExpenseRepo;
import com.example.BalanceTrackerApp.Repository.IncomeRepo;
import com.example.BalanceTrackerApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class statsServiceImple implements statsService {

    @Autowired
    private ExpenseRepo Erepo;

    @Autowired
    private IncomeRepo Irepo;

    @Autowired
    private UserRepo userRepo;

    public StatsDTO getStats(Long userId) {
        // Handle null values safely
        Double totalIncome = Optional.ofNullable(Irepo.sumAllAmountsByUser(userId)).orElse(0.0);
        Double totalExpense = Optional.ofNullable(Erepo.sumAllAmountsByUser(userId)).orElse(0.0);

        // Fetch latest income & expense
        Optional<Income> optionalIncome = Irepo.findFirstByUserIdOrderByDate(userId);
        Optional<Expense> optionalExpense = Erepo.findFirstByUserIdOrderByDate(userId);

        // Initialize DTO
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setIncome(totalIncome);
        statsDTO.setExpense(totalExpense);
        statsDTO.setBalance(totalIncome - totalExpense); // Always safe

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        // Fetch user-specific income & expenses
        List<Income> incomeList = Irepo.findByUserId(userId);
        List<Expense> expenseList = Erepo.findByUserId(userId);

        // Calculate min & max safely
        OptionalDouble minIncome = incomeList.stream()
                .mapToDouble(income -> safeParseDouble(income.getAmount()))
                .min();

        OptionalDouble maxIncome = incomeList.stream()
                .mapToDouble(income -> safeParseDouble(income.getAmount()))
                .max();

        OptionalDouble minExpense = expenseList.stream()
                .mapToDouble(expense -> safeParseDouble(expense.getAmount()))
                .min();

        OptionalDouble maxExpense = expenseList.stream()
                .mapToDouble(expense -> safeParseDouble(expense.getAmount()))
                .max();

        // Set values safely, using 0.0 as a fallback
        statsDTO.setMaxIncome(maxIncome.orElse(0.0));
        statsDTO.setMinIncome(minIncome.orElse(0.0));
        statsDTO.setMaxExpense(maxExpense.orElse(0.0));
        statsDTO.setMinExpense(minExpense.orElse(0.0));

        return statsDTO;
    }

    // Helper method to safely parse amounts
    private double safeParseDouble(String amount) {
        try {
            return amount != null ? Double.parseDouble(amount) : 0.0;
        } catch (NumberFormatException e) {
            System.err.println("Invalid amount format: " + amount);
            return 0.0; // Fallback value
        }
    }

    public GraphDTO getChartdata() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(2);  // Get data from last 2 months

        // Debugging output to check the date range
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        GraphDTO graphDTO = new GraphDTO();

        // Get all users
        List<User> users = userRepo.findAll();  // Fetch all users from UserRepo
        List<Income> allIncomes = new ArrayList<>();
        List<Expense> allExpenses = new ArrayList<>();

        // Loop through all users and fetch their incomes and expenses
        for (User user : users) {
            List<Income> userIncomes = Irepo.findByDateBetweenAndUserId(startDate, endDate, user.getId());
            List<Expense> userExpenses = Erepo.findByDateBetweenAndUserId(startDate, endDate, user.getId());

            allIncomes.addAll(userIncomes);  // Add user's incomes
            allExpenses.addAll(userExpenses);  // Add user's expenses
        }

        // Set the lists in the GraphDTO
        graphDTO.setIncomeList(allIncomes);
        graphDTO.setExpensesList(allExpenses);
        graphDTO.setUsersList(users);  // Set the user list

        return graphDTO;
    }
}
