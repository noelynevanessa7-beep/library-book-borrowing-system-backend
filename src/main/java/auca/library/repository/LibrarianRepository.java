package auca.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.library.domain.Librarian;

@Repository 
public interface LibrarianRepository extends JpaRepository<Librarian, UUID> {
}
