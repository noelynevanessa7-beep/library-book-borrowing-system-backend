package auca.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.library.domain.Loan;

@Repository 
public interface LoanRepository extends JpaRepository<Loan, UUID> {    
} 