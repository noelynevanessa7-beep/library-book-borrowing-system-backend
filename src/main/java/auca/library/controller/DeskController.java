package auca.library.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Desk;
import auca.library.service.DeskService;

@RestController 
@RequestMapping ("/api/desks")
public class DeskController {

    @Autowired 
    private DeskService deskServe;

    @PostMapping ("/save")
    public ResponseEntity<String> saveDesk(@RequestBody Desk desk) {
        return new ResponseEntity<>(deskServe.saveDesk(desk), HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public ResponseEntity<List<Desk>> getAllDesks() {
        return new ResponseEntity<>(deskServe.getAllDesks(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getDeskById(@PathVariable UUID id) {
        Optional<Desk> desk = deskServe.getDeskById(id);
        if  (desk.isEmpty()) {
            return new ResponseEntity<>("The desk with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(desk.get(), HttpStatus.OK);

    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updatedDesk(@PathVariable UUID id, @RequestBody Desk desk) {
        String msg = deskServe.updateDesk(id, desk);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

}