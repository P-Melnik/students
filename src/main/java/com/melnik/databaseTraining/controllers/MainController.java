package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @Autowired
    private StudentsRepository studentsRepository;


    @GetMapping(value = "/", produces = "application/json")
    public String getGreeting() {
        return """
                This is student information resource.
                Yoy can see list of students, create edit and delete info
                Also added student's library information""";
    }

}
