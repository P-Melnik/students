package com.melnik.database_training;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
public class LibraryAccounting {


    @EmbeddedId
    StudentBookReturnDateCompositeKey compositeKey;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    private LocalDate returnDate;
    private LocalDate borrowDate;

    LibraryAccounting() {}

    public LibraryAccounting(StudentBookReturnDateCompositeKey id, Student student, Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.compositeKey = id;
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public StudentBookReturnDateCompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(StudentBookReturnDateCompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryAccounting that = (LibraryAccounting) o;
        return Objects.equals(compositeKey, that.compositeKey) && Objects.equals(student, that.student) && Objects.equals(book, that.book) && Objects.equals(returnDate, that.returnDate) && Objects.equals(borrowDate, that.borrowDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositeKey, student, book, returnDate, borrowDate);
    }
}
