package auca.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.library.domain.Student;
import auca.library.repository.LibrarianRepository;
import auca.library.repository.StudentRepository;

@Service 
public class StudentService {

    @Autowired 
    private StudentRepository studentRepo;

    @Autowired 
    private LibrarianRepository librarianRepo;

    public String saveStudent(Student student) {
        studentRepo.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepo.findById(id);
    }

    public String updateStudent(UUID id, Student updated) {
        Optional<Student> optional = studentRepo.findById(id);
        if (optional.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student student = optional.get();
        student.setFirstName(updated.getFirstName());
        student.setLastName(updated.getLastName());
        student.setStudentNumber(updated.getStudentNumber());
        student.setEmail(updated.getEmail());
        studentRepo.save(student);
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        if (!studentRepo.existsById(id)) {
            return "The student with that id does not exist";
        }
        studentRepo.deleteById(id);
        return "Student deleted successfully";
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Student> getStudentsOfLibrarian(UUID librarianId) {
        if (!librarianRepo.existsById(librarianId)) {
            return null;
        }
        return studentRepo.findStudentsOfLibrarian(librarianId);
    }
    
}
