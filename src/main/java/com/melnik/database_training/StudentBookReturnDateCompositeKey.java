package com.melnik.database_training;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentBookReturnDateCompositeKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "book_id")
    Long bookId;

    public StudentBookReturnDateCompositeKey() {
    }

    public StudentBookReturnDateCompositeKey(Long studentId, Long bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBookReturnDateCompositeKey that = (StudentBookReturnDateCompositeKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, bookId);
    }
}
