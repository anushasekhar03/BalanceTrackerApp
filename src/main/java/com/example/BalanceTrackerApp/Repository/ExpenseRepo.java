package com.example.BalanceTrackerApp.Repository;

import com.example.BalanceTrackerApp.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

    Optional<Expense> findFirstByUserIdOrderByDate(Long userId);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    Double sumAllAmountsByUser(@Param("userId") Long userId);

    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate AND e.user.id = :userId")
    List<Expense> findByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);


}

