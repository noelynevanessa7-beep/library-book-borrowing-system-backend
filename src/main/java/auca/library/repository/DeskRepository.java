package auca.library.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import auca.library.domain.Desk;

@Repository 
public interface DeskRepository  extends JpaRepository<Desk, UUID>{
    
    @Query ("SELECT d FROM Desk d WHERE NOT EXISTS (SELECT l FROM Librarian l WHERE l.desk = d) ORDER BY d.deskNumber ASC")
    List<Desk> findFreeDesks();

    Optional<Desk> findByDeskNumber(int deskNumber);
    
}
