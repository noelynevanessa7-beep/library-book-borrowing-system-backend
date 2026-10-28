package auca.library.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.library.domain.Student;
import auca.library.service.StudentService;

@RestController 
@RequestMapping("/api/students")
public class StudentController {

    @Autowired 
    private StudentService studentServe;

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentServe.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentServe.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable UUID id) {
        Optional<Student> student = studentServe.getStudentById(id);
        if (student.isEmpty()) {
            return new ResponseEntity<>("The student with that id odes not exist", HttpStatus.NOT_FOUND);
        }  
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> ipdateStudent(@PathVariable UUID id, @RequestBody Student student) {
        String msg = studentServe.updateStudent(id, student);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        String msg = studentServe.deleteStudent(id);
        HttpStatus status = HttpStatus.OK;
        if (msg.contains("does not exist")) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(msg, status);
    }

    @GetMapping ("/by-last-name")
    public ResponseEntity<List<Student>> getStudentsByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(studentServe.getStudentsByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping ("/of-librarian/{librarianId}")
    public ResponseEntity<?> getStudentOfLibrarian(@PathVariable UUID librarianId) {
        List<Student> students = studentServe.getStudentsOfLibrarian(librarianId);
        if (students == null) {
            return new ResponseEntity<>("The librarian with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
}
