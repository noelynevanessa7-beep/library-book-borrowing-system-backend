package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Desk;
import auca.library.domain.Section;
import auca.library.domain.Librarian;
import auca.library.repository.DeskRepository;
import auca.library.repository.SectionRepository;
import auca.library.repository.LibrarianRepository;


@Service 
public class LibrarianService {

    @Autowired 
    private LibrarianRepository librarianRepo;

    @Autowired 
    private SectionRepository sectionRepo;

    @Autowired 
    private DeskRepository deskRepo;


    public String saveLibrarian(Librarian librarian) {
        librarianRepo.save(librarian);
        return "Librarian saved successfully";
    }

    public List<Librarian> getAllLibrarians() {
        return librarianRepo.findAll();
    }

    public Optional<Librarian> getLibrarianById(UUID id) {
        return librarianRepo.findById(id);
    }

    public String updateLibrarian(UUID id, Librarian updated) {
        Optional<Librarian> optional = librarianRepo.findById(id);
        if (optional.isEmpty()) {
            return "The librarian with that id does not exist";
        }
        Librarian librarian = optional.get();
        librarian.setFirstName(updated.getFirstName());
        librarian.setLastName(updated.getLastName());
        librarian.setEmail(updated.getEmail());
        if (updated.getDesk() != null) {
            librarian.setDesk(updated.getDesk());
        }
        librarianRepo.save(librarian);
        return "Librarian updated successfully";

    }

    public String deleteLibrarian(UUID id) {
        if (!librarianRepo.existsById(id)) {
            return "The Librarian with taht id does not exist";
        }
        librarianRepo.deleteById(id);
        return "Librarian deleted successfully";
    }

    public List<Librarian> getLibrariansBySecton(String name) {
        return librarianRepo.findLibrariansBySection(name);
    }

    public String addSectionToLibrarian(UUID librarianId, UUID sectionId) {
        Optional<Librarian> optionalLibrarian = librarianRepo.findById(librarianId);
        if (optionalLibrarian.isEmpty()) {
            return "The librarian with that id does not exist";
        }
        Optional<Section> optionalSection = sectionRepo.findById(sectionId);
        if (optionalSection.isEmpty()) {
            return "The section does not exist";
        }
        Librarian librarian = optionalLibrarian.get();
        Section section = optionalSection.get();
        for (Section s : librarian.getSections()) {
            if (s.getId().equals(sectionId)) {
                return "The librarian already has that section";
            }
        }
        librarian.getSections().add(section);
        librarianRepo.save(librarian);
        return "Section added to the librarian";
    }

    public String changeDesk(UUID librarianId, int deskNumber) {
        Optional<Librarian> optionalLibrarian = librarianRepo.findById(librarianId);
        if (optionalLibrarian.isEmpty()) {
            return "The librarian with that id does not exist";
        }
        Optional<Desk> optionalDesk = deskRepo.findByDeskNumber(deskNumber);
        if (optionalDesk.isEmpty()) {
            return "The desk with that deskNumber does not exist";
        }
        Desk desk = optionalDesk.get();
        if (librarianRepo.existsByDeskIdAndIdNot(desk.getId(), librarianId)) {
            return "The desk is taken by another librarian";
        }
        Librarian librarian = optionalLibrarian.get();
        librarian.setDesk(desk);
        librarianRepo.save(librarian);
        return "Desk changed successfully";
    }
    
}
