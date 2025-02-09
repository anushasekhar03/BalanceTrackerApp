package com.example.BalanceTrackerApp.Service;

import com.example.BalanceTrackerApp.DTO.IncomeDTO;
import com.example.BalanceTrackerApp.Entity.Income;
import com.example.BalanceTrackerApp.Entity.User;
import com.example.BalanceTrackerApp.Repository.IncomeRepo;
import com.example.BalanceTrackerApp.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImple implements IncomeService {

    @Autowired
    private IncomeRepo repo;

    @Autowired
    private UserRepo userRepo;

    // This method is used for updating existing income
    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setId(incomeDTO.getId());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setTitle(incomeDTO.getTitle());
        income.setDescription(incomeDTO.getDescription());
        income.setDate(LocalDate.parse(incomeDTO.getDate()));  // Convert date to LocalDate
        return repo.save(income);  // Save the updated income
    }

    // This method is used for creating a new income
    @Override
    public Income postIncome(IncomeDTO dto) {
        // Fetch User using userId
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));

        // Create new Income object and set its fields from DTO
        Income income = new Income();
        income.setTitle(dto.getTitle());
        income.setAmount(dto.getAmount());
        income.setDate(LocalDate.parse(dto.getDate()));  // Convert String to LocalDate
        income.setDescription(dto.getDescription());
        income.setCategory(dto.getCategory());
        income.setUser(user);  // Assign the fetched User to Income

        return repo.save(income);  // Save the new income
    }

    @Override
    public List<Income> getallIncome() {
        return repo.findAll();
    }

    @Override
    public Income getById(Long id) {
        Optional<Income> o = repo.findById(id);
        if (o.isPresent()) {
            return o.get();
        } else {
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }

    @Override
    public Income updateIncome(Long id, IncomeDTO incomeDTO) {
        Optional<Income> optionalIncome = repo.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);  // Use saveOrUpdateIncome to update existing income
        } else {
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }

    @Override
    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = repo.findById(id);
        if (optionalIncome.isPresent()) {
            repo.deleteById(id);  // Delete the income by id
        } else {
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }
}
