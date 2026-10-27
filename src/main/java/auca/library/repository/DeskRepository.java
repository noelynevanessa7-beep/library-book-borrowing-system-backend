package auca.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.library.domain.Desk;

@Repository 
public interface DeskRepository  extends JpaRepository<Desk, UUID>{
    
}
