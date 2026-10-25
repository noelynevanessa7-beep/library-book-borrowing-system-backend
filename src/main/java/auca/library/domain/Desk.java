package auca.library.domain;


import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "desk")
public class Desk {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int deskNumber;

    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getName() {
        return name;
    }

    public void  setName(String name) {
        this.name = name;
    }

}
