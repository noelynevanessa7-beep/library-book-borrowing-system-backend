package auca.library.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.LoanStatus;
import auca.library.repository.DeskRepository;
import auca.library.repository.FineRepository;
import auca.library.repository.LibrarianRepository;
import auca.library.repository.LoanRepository;
import auca.library.repository.StudentRepository;

@Service 
public class DashboardService {

    @Autowired 
    private StudentRepository studentRepo;

    @Autowired 
    private LibrarianRepository librarianRepo;

    @Autowired 
    private DeskRepository deskRepo;

    @Autowired 
    private LoanRepository loanRepo;

    @Autowired 
    private FineRepository fineRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>(); 
        summary.put("students", studentRepo.count());
        summary.put("librarians",  librarianRepo.count());
        summary.put("freeDesks", (long) deskRepo.findFreeDesks().size());
        summary.put("loansToday", loanRepo.countByLoanDate(LocalDate.now()));
        summary.put("requested", loanRepo.countByStatus(LoanStatus.REQUESTED));
        summary.put("fines", fineRepo.count());
        return summary;
    }
    
}
