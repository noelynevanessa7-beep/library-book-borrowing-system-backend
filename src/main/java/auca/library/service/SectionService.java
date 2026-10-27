package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Section;
import auca.library.repository.SectionRepository;

@Service 
public class SectionService {

    @Autowired 
    private SectionRepository sectionRepo;

    public String saveSection(Section section) {
        sectionRepo.save(section);
        return "Section saved successfully";
    }

    public List<Section> getAllSections() {
        return sectionRepo.findAll();
    }
    
    public Optional<Section> getSectionById(UUID Id) {
        return sectionRepo.findById(Id);
    }

    public String updateSection(UUID Id, Section updated) {
        Optional<Section> optional = sectionRepo.findById(Id);
        if (optional.isEmpty()) {
            return "The section with that id does not exist";
        }
        Section section = optional.get();
        section.setName(updated.getName());
        sectionRepo.save(section);
        return "Section updated successfully";
    }

    public String deleteSection(UUID id) {
        if (!sectionRepo.existsById(id)) {
            return "The section with that id does not exist";
        }
        sectionRepo.deleteById(id);
        return "Section deleted successfully";
    }
}
