package com.melnik.database_training.controllers;

import com.melnik.database_training.Book;
import com.melnik.database_training.LibraryAccounting;
import com.melnik.database_training.repo.BookRepository;
import com.melnik.database_training.repo.LibraryAccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryAccountingRepository libraryAccountingRepository;

    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(b -> ResponseEntity.ok().body(b))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/borrowed")
    public List<LibraryAccounting> findBorrowed() {
        return libraryAccountingRepository.borrowed();
    }

    @GetMapping("/borrowed/all")
    public List<LibraryAccounting> findBorrowedAndReturned() {
        return libraryAccountingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        Book created = bookRepository.save(book);
        return new ResponseEntity<>(created.getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBook(@PathVariable("id") long id, @RequestBody Book book) {
        if (bookRepository.existsById(id)) {
            bookRepository.update(id, book);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
