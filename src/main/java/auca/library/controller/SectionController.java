package auca.library.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Section;
import auca.library.service.SectionService;

@RestController 
@RequestMapping("/api/sections")
public class SectionController {

    @Autowired 
    private SectionService sectionServe; 

    @PostMapping("/save")
    public ResponseEntity<String> saveSection(@RequestBody Section section) {
        return new ResponseEntity<>(sectionServe.saveSection(section), HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public ResponseEntity<List<Section>> getAllSections() {
        return new ResponseEntity<>(sectionServe.getAllSections(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getSectionById(@PathVariable UUID id) {
        Optional<Section> section = sectionServe.getSectionById(id);
        if (section.isEmpty()) {
            return new ResponseEntity<>("The section with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(section.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateSection(@PathVariable  UUID id, @RequestBody Section section) {
        String msg = sectionServe.updateSection(id, section);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable UUID id) {
        String msg = sectionServe.deleteSection(id);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }
}