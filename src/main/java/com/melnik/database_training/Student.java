package com.melnik.database_training;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "student")
    Set<LibraryAccounting> libraryAccountingSet;

    private String fullName;
    private LocalDate dob;
    private String faculty;
    private StudentStatus studentStatus;
    private String address;
    private String phoneNumber;

    public Student() {}

    public Student(String fullName, LocalDate dob, String faculty, StudentStatus studentStatus) {
        this.fullName = fullName;
        this.dob = dob;
        this.faculty = faculty;
        this.studentStatus = studentStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
