package com.example.BalanceTrackerApp.DTO;


import com.example.BalanceTrackerApp.Entity.Expense;
import com.example.BalanceTrackerApp.Entity.Income;

public class StatsDTO {

    private Double income;
    private Double expense;

    private Income latestIncome;
    private Expense LatestExpense;

    private Double balance;

    private Double minIncome;
    private Double maxIncome;

    private Double minExpense;
    private Double maxExpense;

    public StatsDTO(Double income, Double expense, Income latestIncome,
                    Expense latestExpense, Double balance, Double minIncome,
                    Double maxIncome, Double minExpense, Double maxExpense) {
        this.income = income;
        this.expense = expense;
        this.latestIncome = latestIncome;
        LatestExpense = latestExpense;
        this.balance = balance;
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.minExpense = minExpense;
        this.maxExpense = maxExpense;
    }

    public StatsDTO() {
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Income getLatestIncome() {
        return latestIncome;
    }

    public void setLatestIncome(Income latestIncome) {
        this.latestIncome = latestIncome;
    }

    public Expense getLatestExpense() {
        return LatestExpense;
    }

    public void setLatestExpense(Expense latestExpense) {
        LatestExpense = latestExpense;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(Double minIncome) {
        this.minIncome = minIncome;
    }

    public Double getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(Double maxIncome) {
        this.maxIncome = maxIncome;
    }

    public Double getMinExpense() {
        return minExpense;
    }

    public void setMinExpense(Double minExpense) {
        this.minExpense = minExpense;
    }

    public Double getMaxExpense() {
        return maxExpense;
    }

    public void setMaxExpense(Double maxExpense) {
        this.maxExpense = maxExpense;
    }
}
