package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;

import auca.library.domain.Loan;
import auca.library.domain.LoanStatus;
import auca.library.repository.FineRepository;
import auca.library.repository.LoanRepository;

@Service 
public class LoanService {

    @Autowired 
    private LoanRepository loanRepo;

    @Autowired 
    private FineRepository fineRepo;

    @Transactional 
    public String cancelDay(UUID librarianId, LocalDate date) {
        int count = loanRepo.cancelLoansOfDay(LoanStatus.REJECTED, librarianId, date, LoanStatus.RETURNED);
        return count + "loans cancelled";
    }

    public String saveLoan(Loan loan) {
        if (loan.getLoanDate().isBefore(LocalDate.now())) {
            return "Loan date cannot be in the past";
        }
        boolean busy = loanRepo.existsByLibrarianIdAndLoanDateAndStatusNot(loan.getLibrarian().getId(), loan.getLoanDate(), LoanStatus.REJECTED);
        if (busy) {
            return "Librarian already issues a loan on that date";
        }
        loan.setStatus(LoanStatus.REQUESTED);
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
        if (fineRepo.existsByLoanId(id)) {
            return "That loan has fines, delete them first";
        }
        loanRepo.deleteById(id);
        return "Loan deleted successfully";
    }

    public List<Loan> getLoansByStatus(LoanStatus status) {
        return loanRepo.findByStatusOrderByLoanDateAsc(status);
    }

    public List<Loan> getLoanBetween(LocalDate start, LocalDate end) {
        return loanRepo.findByLoanDateBetweenOrderByLoanDateAsc(start, end);
    }

    public String changeStatus(UUID id, LoanStatus newStatus) {
        Optional<Loan> optional = loanRepo.findById(id);
        if (optional.isEmpty()) {
            return "The loan with that id does not exist";
        }
        Loan loan = optional.get();
        LoanStatus current = loan.getStatus();
        boolean allowed = false;
        if (current == LoanStatus.REQUESTED) {
            if (newStatus == LoanStatus.ISSUED || newStatus == LoanStatus.REJECTED) {
                allowed = true;
            }
        } else if (current == LoanStatus.ISSUED) {
            if (newStatus == LoanStatus.RETURNED || newStatus == LoanStatus.REJECTED) {
                    allowed = true;
            }
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        loan.setStatus(newStatus);
        loanRepo.save(loan);
        return "Loan status updated successfully";
    }

    public List<Object[]> getLoanStats() {
        return loanRepo.countLoanByStatus();
    }
    
    
}
