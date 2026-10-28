package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Desk;
import auca.library.repository.DeskRepository;

@Service 
public class DeskService {

    @Autowired 
    private DeskRepository deskRepo;

    public String saveDesk(Desk desk) {
        deskRepo.save(desk);
        return "Desk saved successfully";
    }

    public List<Desk> getAllDesks() {
        return deskRepo.findAll();
    }

    public Optional<Desk> getDeskById(UUID id) {
        return deskRepo.findById(id);
    }

    public String updateDesk(UUID id, Desk updated) {
        Optional<Desk> optional = deskRepo.findById(id);
        if (optional.isEmpty()) {
            return "The desk with that id does not exist";
        }
        Desk desk = optional.get();
        desk.setDeskNumber(updated.getDeskNumber());
        desk.setName(updated.getName());
        deskRepo.save(desk);
        return "Desk updated successfully";
    }

    public String deleteDesk(UUID id) {
        if (!deskRepo.existsById(id)) {
            return "The desk with that id does not exist";
        }
        deskRepo.deleteById(id);
        return "Desk deleted successfully";
    }

    public List<Desk> getFreeDesks() {
        return deskRepo.findFreeDesks();
    }
    
}
