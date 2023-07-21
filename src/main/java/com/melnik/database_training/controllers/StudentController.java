package com.melnik.database_training.controllers;

import com.melnik.database_training.*;
import com.melnik.database_training.repo.BookRepository;
import com.melnik.database_training.repo.LibraryAccountingRepository;
import com.melnik.database_training.repo.StudentsRepository;
import com.melnik.database_training.utils.StudentMapper;
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


    // send JSON without ID param cause ID is autogenerated
    @PostMapping()
    public ResponseEntity<StudentID> add(@RequestBody Student student) {
        Student newStudent = studentsRepository.save(student);
        StudentID created = new StudentID(newStudent.getId());
        return new ResponseEntity<>(created, HttpStatus.OK);
    }


    // send JSON without ID param cause ID is taken from the path
    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable("id") long id, @RequestBody Student student) {
        if (studentsRepository.update(id, student) != 0) {
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR - NO SUCH STUDENT ID",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        if (studentsRepository.delete(id) != 0) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR - NO SUCH STUDENT ID",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/books/{bookId}/borrow")
    public ResponseEntity<?> addBook(@PathVariable("id") long id,
                                     @PathVariable("bookId") long bookId) {
        if (studentsRepository.existsById(id)
                && bookRepository.existsById(bookId)
                && bookRepository.amount(bookId) > 0
                && libraryAccountingRepository.bookIsBorrowedByThisStudent(id, bookId) == null) {
            LibraryAccounting libraryAccounting = new LibraryAccounting(id, bookId);
            libraryAccounting.setBorrowDate(LocalDate.now());
            libraryAccountingRepository.save(libraryAccounting);
            bookRepository.balanceMinus(bookId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/books/{bookId}/return")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id,
                                        @PathVariable("bookId") long bookId) {
        long libraryId = libraryAccountingRepository.getLibraryId(id, bookId);
        if (studentsRepository.existsById(id)
                && libraryAccountingRepository.existsById(libraryId)) {
            libraryAccountingRepository.update(libraryId, LocalDate.now());
            bookRepository.balancePlus(bookId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/borrowed")
    public List<LibraryAccounting> borrowedByStudent(@PathVariable("id") long id) {
        return libraryAccountingRepository.borrowedByStudent(id);
    }

}
