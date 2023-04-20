package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController

public class MainController {

    @Autowired
    private StudentsRepository studentsRepository;


    @GetMapping(value = "/", produces = "application/json")
    public String getGreeting() {
        return "This is student information resource.\n" +
                "To add new send post request to /student\n" +
                "To delete student send delete request to /student/id\n" +
                "To edit send put request to /student/id\n" +
                "To get list of students send get request to /student\n" +
                "To sort list use query parameters like /student?param1=value1";
    }

}
