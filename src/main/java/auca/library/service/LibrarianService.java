package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Librarian;
import auca.library.repository.LibrarianRepository;

@Service 
public class LibrarianService {

    @Autowired 
    private LibrarianRepository librarianRepo;

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
    
}
