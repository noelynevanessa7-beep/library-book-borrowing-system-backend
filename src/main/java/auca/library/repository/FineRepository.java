package auca.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import auca.library.domain.Fine;

@Repository 
public interface FineRepository extends JpaRepository<Fine, UUID> {

    @Query("SELECT d FROM Fine d WHERE d.loan.student.studentNumber = :studentNumber ORDER BY d.chargedDate DESC")
    List<Fine> findFinesOfStudent(@Param("studentNumber") String studentNumber);

    boolean existsByLoanId(UUID loanId);

}
