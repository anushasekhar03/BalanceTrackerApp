package com.example.BalanceTrackerApp.Service.Stats;

import com.example.BalanceTrackerApp.DTO.GraphDTO;
import com.example.BalanceTrackerApp.DTO.StatsDTO;
import org.springframework.stereotype.Service;

@Service
public interface statsService {

    StatsDTO getStats(Long userId);

    GraphDTO getChartdata();
}
