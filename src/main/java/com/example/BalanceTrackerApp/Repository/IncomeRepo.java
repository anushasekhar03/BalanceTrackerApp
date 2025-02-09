package com.example.BalanceTrackerApp.Repository;

import com.example.BalanceTrackerApp.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {
}
