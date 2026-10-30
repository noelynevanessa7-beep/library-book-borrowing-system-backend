package auca.library.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity 
@Table(name = "fine")
public class Fine {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String reason;

    private String notes;

    private int amountRwf;

    private LocalDate chargedDate;

    @ManyToOne 
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getChargedDate() {
        return chargedDate;
    }

    public void setChargedDate(LocalDate chargedDate) {
        this.chargedDate = chargedDate;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    
}
