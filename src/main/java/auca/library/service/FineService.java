package auca.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Fine;
import auca.library.domain.Loan;
import auca.library.domain.LoanStatus;
import auca.library.repository.FineRepository;
import auca.library.repository.LoanRepository;

@Service 
public class FineService {

     @Autowired 
     private FineRepository fineRepo;

     @Autowired 
     private LoanRepository loanRepo;

     public String saveFine(Fine fine, UUID loanId) {
        Optional<Loan> optional = loanRepo.findById(loanId);
        if (optional.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = optional.get();
        if (loan.getStatus() != LoanStatus.RETURNED) {
            return "Fines are only for RETURNED loans";
        }
        fine.setChargedDate(LocalDate.now());
        fine.setLoan(loan);
        fineRepo.save(fine);
        return "Fine saved successfully";
     }

     public List<Fine> getFinesOfStudent(String studentNumber) {
        return fineRepo.findFinesOfStudent(studentNumber);
     }

     public String deleteFine(UUID id) {
         if (!fineRepo.existsById(id)) {
            return "The fine with that id does not exist";
        }
        fineRepo.deleteById(id);
        return "Fine deleted successfully";
     }
    
}
