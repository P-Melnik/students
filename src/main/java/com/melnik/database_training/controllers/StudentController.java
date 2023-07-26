package com.melnik.database_training.controllers;

import com.melnik.database_training.*;
import com.melnik.database_training.repo.BookRepository;
import com.melnik.database_training.repo.LibraryAccountingRepository;
import com.melnik.database_training.repo.StudentsRepository;
import com.melnik.database_training.utils.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryAccountingRepository libraryAccountingRepository;

    @GetMapping
    public List<StudentDTO> getAllOrByStatus(@RequestParam(required = false) StudentStatus studentStatus) {
        if (studentStatus == null) {
            return studentsRepository.findAll().stream()
                    .map(StudentMapper::mapToStudentDTO)
                    .collect(Collectors.toList());
        }
        return studentsRepository.list(studentStatus).stream()
                .map(StudentMapper::mapToStudentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getByID(@PathVariable("id") long id) {
        Optional<StudentDTO> student = studentsRepository.findById(id)
                .map(StudentMapper::mapToStudentDTO);
        return student.map(s -> ResponseEntity.ok().body(s))
                .orElse(ResponseEntity.notFound().build());
    }

    // FOR DEVELOPMENT
    @GetMapping("/{id}/admin")
    public ResponseEntity<Student> getByIDAdmin(@PathVariable("id") long id) {
        Optional<Student> student = studentsRepository.findById(id);
        Student student1 = student.get();
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<StudentId> add(@RequestBody Student student) {
        Student newStudent = studentsRepository.save(student);
        StudentId created = new StudentId(newStudent.getId());
        return new ResponseEntity<>(created, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable("id") long id, @RequestBody Student student) {
        if (studentsRepository.update(id, student) != 0) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR - NO SUCH STUDENT ID", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (studentsRepository.delete(id) != 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR - NO SUCH STUDENT ID",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("/{studentId}/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable("studentId") long studentId,
                                             @PathVariable("bookId") long bookId) {
        LocalDate date = LocalDate.now();
        if (libraryAccountingRepository.borrowBook(studentId, bookId, date) == 0) {
            return new ResponseEntity<>("Is already borrowed",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            bookRepository.balanceMinus(bookId);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
    }

    @Transactional
    @PutMapping("/{studentId}/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable("studentId") long studentId,
                                             @PathVariable("bookId") long bookId) {
        LocalDate date = LocalDate.now();
        if (libraryAccountingRepository.returnBook(studentId, bookId, date) == 0) {
            return new ResponseEntity<>("WRONG ID OR BOOK IS ALREADY RETURNED",
                    HttpStatus.NOT_FOUND);
        } else {
            bookRepository.balancePlus(bookId);
            return new ResponseEntity<>("Successfully returned",
                    HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/borrowed")
    public List<LibraryAccounting> borrowedByStudent(@PathVariable("id") long id) {
        return libraryAccountingRepository.borrowedByStudent(id);
    }

}

