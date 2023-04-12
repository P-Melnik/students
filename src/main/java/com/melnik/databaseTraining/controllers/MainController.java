package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController

public class MainController {

    @Autowired
    private StudentsRepository studentsRepository;


    @GetMapping(value = "/", produces = "application/json")
    public String getGreeting() {
        return "This is student information resource.\n" +
                "To see all students go to /student/all\n" +
                "To see all current students go to student/current\n" +
                "To see all graduated students go to student/graduated\n" +
                "To see all expelled students go to student/expelled\n" +
                "To see all students on vacation go to student/vacation\n" +
                "To add new student print 'add'\n" +
                "To delete student print 'delete'\n" +
                "To edit print 'edit'";
    }

    @PostMapping("/")
    public RedirectView selection(@RequestBody String request) {
        return new RedirectView("/student/" + request);
    }

    @GetMapping("/student/all")
    public List<Student> getAll() {
        List<Student> students = studentsRepository.findAll();
        return students;
    }

    @GetMapping("student/graduated")
    public List<Student> getGraduated() {
        List<Student> students = studentsRepository.selectByStatus(0);
        return students;
    }

    @GetMapping("student/current")
    public List<Student> getCurrent() {
        List<Student> students = studentsRepository.selectByStatus(1);
        return students;
    }


    @GetMapping("student/expelled")
    public List<Student> getExpelled() {
        List<Student> students = studentsRepository.selectByStatus(2);
        return students;
    }

    @GetMapping("student/vacation")
    public List<Student> getOnVacation() {
        List<Student> students = studentsRepository.selectByStatus(3);
        return students;
    }

}
