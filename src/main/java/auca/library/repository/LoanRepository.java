package auca.library.repository;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import auca.library.domain.Loan;
import auca.library.domain.LoanStatus;

@Repository 
public interface LoanRepository extends JpaRepository<Loan, UUID> {   
    
    List<Loan> findByStatusOrderByLoanDateAsc(LoanStatus status);

    List<Loan> findByLoanDateBetweenOrderByLoanDateAsc(LocalDate start, LocalDate end);

    boolean existsByLibrarianIdAndLoanDateAndStatusNot(UUID librarianId, LocalDate loanDate, LoanStatus status);

    @Query ("SELECT l.status, COUNT(l) FROM Loan l GROUP BY l.status")
    List<Object[]> countLoanByStatus();

    @Modifying 
    @Query ("UPDATE Loan l SET l.status = :rejected WHERE l.librarian.id = :librarianId AND l.loanDate = :date AND l.status <> :returned")
    int cancelLoansOfDay(@Param("rejected") LoanStatus rejected, @Param("librarianId") UUID librarianId, @Param("date") LocalDate date, @Param("returned") LoanStatus returned);

} 