package com.melnik.database_training.controllers;

import com.melnik.database_training.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/healthcheck")
    public ResponseEntity<?> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
