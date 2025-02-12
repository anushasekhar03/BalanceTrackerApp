package com.example.BalanceTrackerApp.Repository;

import com.example.BalanceTrackerApp.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);

    Optional<Income> findFirstByUserIdOrderByDate(Long userId);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user.id = :userId")
    Double sumAllAmountsByUser(@Param("userId") Long userId);

    @Query("SELECT i FROM Income i WHERE i.date BETWEEN :startDate AND :endDate AND i.user.id = :userId")
    List<Income> findByDateBetweenAndUserId(LocalDate startDate, LocalDate endDate, Long userId);

}

