package auca.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Fine;
import auca.library.service.FineService;

@RestController 
@RequestMapping("/api/fines")
public class FineController {

    @Autowired 
    private FineService fineServe;

    @PostMapping("/save")
    public ResponseEntity<String> saveFine(@RequestBody Fine fine, @RequestParam UUID loanId) {
        String msg = fineServe.saveFine(fine, loanId);
        HttpStatus status = HttpStatus.CREATED;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        } else if (msg.contains("only for RETURNED")) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, status);
    }

    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<List<Fine>> getFinesOfStudent(@PathVariable String studentNumber) {
        return new ResponseEntity<>(fineServe.getFinesOfStudent(studentNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFine(@PathVariable UUID id) {
        String msg = fineServe.deleteFine(id);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }
    
}
