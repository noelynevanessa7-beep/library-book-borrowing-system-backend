package auca.library.domain;

import java.time.LocalDate;
import java.util.UUID;

import auca.library.domain.Librarian;
import auca.library.domain.LoanStatus;
import auca.library.domain.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "loan")
public class Loan {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate loanDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private String bookTitle;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "librarian_id")
    private Librarian librarian;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Student getStudent() {
        return  student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
  
}
