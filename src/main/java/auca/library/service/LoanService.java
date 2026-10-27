package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Loan;
import auca.library.repository.LoanRepository;

@Service 
public class LoanService {

    @Autowired 
    private LoanRepository loanRepo;

    public String saveLoan(Loan loan) {
        loanRepo.save(loan);
        return "Loan saved successfully";
    }

    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    public Optional<Loan> getLoanById(UUID id) {
        return loanRepo.findById(id);
    }

    public String updateLoan(UUID id, Loan updated) {
        Optional<Loan> optional = loanRepo.findById(id);
        if (optional.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = optional.get();
        loan.setLoanDate(updated.getLoanDate());
        loan.setBookTitle(updated.getBookTitle());
        if (updated.getStudent() != null) {
            loan.setStudent(updated.getStudent());
        }
        if (updated.getLibrarian() !=null) {
            loan.setLibrarian(updated.getLibrarian());
        }
        loanRepo.save(loan);
        return "Loan updated successfully";
    }

    public String deleteLoan(UUID id) {
        if (!loanRepo.existsById(id)) {
            return "The loan with that id does not exist";
        }
        loanRepo.deleteById(id);
        return "Loan deleted successfully";
    }
    
}
