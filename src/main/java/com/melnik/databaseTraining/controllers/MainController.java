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


    @GetMapping("/")
    public String getGreeting() {
        return "This is student information resource.\n" +
                "To see all students go to /students\n" +
                "To see all current students go to /current\n" +
                "To see all graduated students go to /graduated\n" +
                "To see all expelled students go to/expelled\n" +
                "To see all students on vacation go to /vacation\n" +
                "To add new student print 'add'\n" +
                "To delete student print 'delete'\n" +
                "To edit print 'edit'";
    }

    @PostMapping("/")
    public RedirectView selection(@RequestBody String request) {
        return new RedirectView("/" + request);
    }

    @GetMapping("/students")
    public List<Student> getAll() {
        List<Student> students = studentsRepository.findAll();
        return students;
    }

    @GetMapping("/graduated")
    public List<Student> getGraduated() {
        List<Student> students = studentsRepository.selectGraduated();
        return students;
    }


    @GetMapping("/expelled")
    public List<Student> getExpelled() {
        List<Student> students = studentsRepository.selectExpelled();
        return students;
    }

    @GetMapping("/vacation")
    public List<Student> getOnVacation() {
        List<Student> students = studentsRepository.selectVacation();
        return students;
    }

    @GetMapping("/current")
    public List<Student> getCurrent() {
        List<Student> students = studentsRepository.selectCurrent();
        return students;
    }

}
