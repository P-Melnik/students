package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping
    public String deleting() {

        return "To delete student post id";
    }

    @PostMapping
    public void delete(@RequestBody long id) {
        studentsRepository.deleteById(id);
    }

}
