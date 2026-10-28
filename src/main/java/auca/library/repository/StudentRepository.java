package auca.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import auca.library.domain.Student;

@Repository 
public interface StudentRepository extends JpaRepository<Student, UUID>{

    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query ("SELECT DISTINCT l.student FROM Loan l WHERE l.librarian.id = :librarianId")
    List<Student> findStudentsOfLibrarian(@Param ("librarianId") UUID librarianId);
}
