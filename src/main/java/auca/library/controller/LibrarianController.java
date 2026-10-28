package auca.library.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Librarian;
import auca.library.service.LibrarianService;

@RestController 
@RequestMapping("/api/librarians")
public class LibrarianController {

    @Autowired 
    private LibrarianService librarianServe;

    @PostMapping("/save")
    public ResponseEntity<String> saveLibrarian(@RequestBody Librarian librarian) {
        return new ResponseEntity<>(librarianServe.saveLibrarian(librarian), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Librarian>> getAllLibrarians() {
        return new ResponseEntity<>(librarianServe.getAllLibrarians(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getLibrarianById(@PathVariable UUID id) {
        Optional<Librarian> librarian = librarianServe.getLibrarianById(id);
        if (librarian.isEmpty()) {
            return new ResponseEntity<>("The librarian with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(librarian.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLibrarian(@PathVariable UUID id, @RequestBody Librarian librarian) {
        String msg = librarianServe.updateLibrarian(id, librarian);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibrarian(@PathVariable UUID id) {
        String msg = librarianServe.deleteLibrarian(id);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @GetMapping ("/by-section")
    public ResponseEntity<List<Librarian>> getLibrarianBySection(@RequestParam String name) {
        return new ResponseEntity<>(librarianServe.getLibrariansBySecton(name), HttpStatus.OK);
    }
    
}
