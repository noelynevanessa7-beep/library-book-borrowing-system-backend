package auca.library.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Loan;
import auca.library.domain.LoanStatus;
import auca.library.service.LoanService;

@RestController 
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired 
    private LoanService loanServe;

    @PostMapping("/save")
    public ResponseEntity<String> saveLoan(@RequestBody Loan loan) {
        return new ResponseEntity<>(loanServe.saveLoan(loan), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanServe.getAllLoans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable UUID id) {
        Optional<Loan> loan = loanServe.getLoanById(id);
        if (loan.isEmpty()) {
            return new ResponseEntity<>("The loan with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loan.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLoan(@PathVariable UUID id, @RequestBody Loan loan) {
        String msg = loanServe.updateLoan(id, loan);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable UUID id) {
        String msg = loanServe.deleteLoan(id);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @GetMapping ("/by-status")
    public ResponseEntity<List<Loan>> getLoansByStatus(@RequestParam  LoanStatus status) {
        return new ResponseEntity<>(loanServe.getLoansByStatus(status), HttpStatus.OK);
    }

    @GetMapping ("/between")
    public ResponseEntity<List<Loan>> getLoansBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(loanServe.getLoanBetween(startDate, endDate), HttpStatus.OK);
    }
}