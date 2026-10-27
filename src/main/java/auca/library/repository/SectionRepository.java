package auca.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.library.domain.Section;

@Repository 
public interface SectionRepository extends JpaRepository<Section, UUID> {
}
