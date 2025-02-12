package com.example.BalanceTrackerApp.Controller;

import com.example.BalanceTrackerApp.DTO.GraphDTO;
import com.example.BalanceTrackerApp.DTO.StatsDTO;
import com.example.BalanceTrackerApp.Service.Stats.statsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class statsController {

    @Autowired
    private statsService statsService;

    @GetMapping("/{userId}")
    public StatsDTO getStats(@PathVariable Long userId) {
        return statsService.getStats(userId);
    }

    @GetMapping("/chart")
    public ResponseEntity<GraphDTO> getChartDetails() {
        GraphDTO graphDTO = statsService.getChartdata();

        // Check if the response is empty
        if (graphDTO.getIncomeList().isEmpty() && graphDTO.getExpensesList().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(graphDTO);
    }
}
