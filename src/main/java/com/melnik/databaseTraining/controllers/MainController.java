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
                To add new send post request to /student
                To delete student send delete request to /student/id
                To edit send put request to /student/id
                To get list of students send get request to /student
                To sort list use query parameters like /student?param1=value1""";
    }

}
