package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.StudentStatus;
import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping(value = "/add", produces = "application/json")
    public String instructionsAdd() {
        return "To add a student write information about him like example\n" +
                "John Doe/1991-09-13/Software Development/g\n" +
                "    name/(YYYY-MM-DD)/faculty/status: g for Graduated, " +
                "c for Current, v for Student on vacation, e for Expelled\n" +
                "P.S. In our university nowadays three faculties:\n" +
                "1. Software Development\n" +
                "2. Poetry\n" +
                "3. Musical\n";
    }

    @GetMapping(value = "/edit", produces = "application/json")
    public String instructionsEdit() {
        return "To edit a student print\n" +
                "id/name(new or -)/dob(new or -)/faculty(new or -)/status(new or -)";
    }

    @GetMapping(value = "/delete", produces = "application/json")
    public String instructionsDelete() {
        return "To delete student post id";
    }

    @PostMapping("/add")
    public void makeAdding(@RequestBody String post) {
        String[] data = post.split("/");
        Student student = new Student();
        student.setName(data[0]);
        student.setDob(LocalDate.parse(data[1]));
        student.setFaculty(data[2]);
        student.setStudentStatus(determineStatus(data[3]));
        studentsRepository.save(student);
    }


    @PostMapping("/edit")
    public ResponseEntity makeEditing(@RequestBody String string) {
        String name, faculty;
        StudentStatus status;
        LocalDate dob;
        String[] arr = string.split("/");
        long id = Long.valueOf(arr[0]);
        if (studentsRepository.existsById(id)) {
            Student student = studentsRepository.findById(id).get();
            if (arr[1].equals("-")) {
                name = student.getName();
            } else name = arr[1];
            if (arr[2].equals("-")) {
                dob = student.getDob();
            } else dob = LocalDate.parse(arr[2]);
            if (arr[3].equals("-")) {
                faculty = student.getFaculty();
            } else faculty = arr[3];
            if (arr[4].equals("-")) {
                status = student.getStatus();
            } else status = determineStatus(arr[4]);
            Student newStudent = new Student();
            newStudent.setName(name);
            newStudent.setDob(dob);
            newStudent.setFaculty(faculty);
            newStudent.setStudentStatus(status);
            studentsRepository.deleteById(id);
            studentsRepository.save(newStudent);
            return new ResponseEntity(HttpStatus.FOUND);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody Long id) {
        if (studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.FOUND);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public static StudentStatus determineStatus(String string) {
        switch (string) {
            case "g":
                return StudentStatus.GRADUATED;
            case "c":
                return StudentStatus.CURRENT;
            case "e":
                return StudentStatus.EXPELLED;
            case "v":
                return StudentStatus.VACATION;
        }
        return StudentStatus.UNKNOWN;
    }


}

