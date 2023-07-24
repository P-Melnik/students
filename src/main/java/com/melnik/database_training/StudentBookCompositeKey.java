package com.melnik.database_training;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentBookCompositeKey implements Serializable {

    @Column(name = "student_id")
    Long StudentId;

    @Column(name = "book_id")
    Long BookId;

    public StudentBookCompositeKey(Long studentId, Long bookId) {
        StudentId = studentId;
        BookId = bookId;
    }

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBookCompositeKey that = (StudentBookCompositeKey) o;
        return Objects.equals(StudentId, that.StudentId) && Objects.equals(BookId, that.BookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentId, BookId);
    }
}
