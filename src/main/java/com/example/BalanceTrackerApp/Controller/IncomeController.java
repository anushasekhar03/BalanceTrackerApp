package com.example.BalanceTrackerApp.Controller;

import com.example.BalanceTrackerApp.DTO.IncomeDTO;
import com.example.BalanceTrackerApp.Entity.Income;
import com.example.BalanceTrackerApp.Service.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    private IncomeService service;

    @PostMapping
    public ResponseEntity<?>addIncome(@RequestBody IncomeDTO dto){
        Income created=service.postIncome(dto);
        if(created!=null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(service.getallIncome());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncome(@PathVariable Long id) {
        try {
            Income income = service.getById(id);  // Get Income by ID
            return ResponseEntity.ok(income);  // Return the full income including user
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateIncome(@PathVariable Long id, @RequestBody IncomeDTO dto){
        try {
            Income updatedIncome = service.updateIncome(id, dto); // Correctly passing the incoming DTO
            return ResponseEntity.ok(updatedIncome);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletebyId(@PathVariable Long id){
        try{
            service.deleteIncome(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
