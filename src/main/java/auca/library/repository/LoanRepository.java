package auca.library.repository;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.library.domain.Loan;
import auca.library.domain.LoanStatus;

@Repository 
public interface LoanRepository extends JpaRepository<Loan, UUID> {   
    
    List<Loan> findByStatusOrderByLoanDateAsc(LoanStatus status);

    List<Loan> findByLoanDateBetweenOrderByLoanDateAsc(LocalDate start, LocalDate end);

    boolean existsByLibrarianIdAndLoanDateAndStatusNot(UUID librarianId, LocalDate loanDate, LoanStatus status);
} 