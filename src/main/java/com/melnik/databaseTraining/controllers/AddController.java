package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping("/add")
public class AddController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping
    public String adding() {
        return "To add a student write information about him like example\n" +
                "John Doe/1991-09-13/Software Development/g\n" +
                "    name/(YYYY-MM-DD)/faculty/status: g for Graduated, " +
                "c for Current, v for Student on vacation, e for Expelled\n" +
                "P.S. In our university nowadays three faculties:\n" +
                "1. Software Development\n" +
                "2. Poetry\n" +
                "3. Musical";
    }

    @PostMapping
    public void makeAdding(@RequestBody String post) {
        String[] data = post.split("/");
        Student student = new Student();
        student.setName(data[0]);
        student.setDob(transformToDate(data[1]));
        student.setFaculty(data[2]);
        student.setStudentStatus(determineStatus(data[3]));
        studentsRepository.save(student);
    }

    private LocalDate transformToDate(String string) {
        String[] arr = string.split("-");
        int year = Integer.valueOf(arr[0]);
        int day = Integer.valueOf(arr[2]);
        LocalDate date = (LocalDate.of(year, determineMonth(arr[1]), day));
        return date;
    }

    private Month determineMonth(String string) {
        switch (string) {
            case "01": return Month.JANUARY;
            case "02": return Month.FEBRUARY;
            case "03": return Month.MARCH;
            case "04": return Month.APRIL;
            case "05": return Month.MAY;
            case "06": return Month.JUNE;
            case "07": return Month.JULY;
            case "08": return Month.AUGUST;
            case "09": return Month.SEPTEMBER;
            case "10": return Month.OCTOBER;
            case "11": return Month.NOVEMBER;
            case "12": return Month.DECEMBER;
        } return Month.JANUARY;
    }

    private String determineStatus(String string) {
        switch (string) {
            case "g": return "Graduated";
            case "c": return "Current";
            case "e": return "Expelled";
            case "v": return "Student on vacation";
        } return "";
    }
}
