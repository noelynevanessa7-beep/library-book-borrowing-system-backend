package auca.library.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import auca.library.domain.Section;

@Repository 
public interface SectionRepository extends JpaRepository<Section, UUID> {

    @Query ("SELECT s FROM Section s WHERE s.librarians IS EMPTY")
    List<Section> findUnusedSections();
}
