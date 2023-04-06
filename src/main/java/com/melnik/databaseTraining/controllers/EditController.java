package com.melnik.databaseTraining.controllers;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.repo.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;


@RestController
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping
    public String editing() {

        return "To edit a student print id/name(new or -)" +
                "/dob(new or -)/faculty(new or -)/status(new or -)";
    }

    @PostMapping
    public void makeEditing(@RequestBody String string) {
        String name, faculty, status;
        LocalDate dob;
        String[] arr = string.split("/");
        long id = Long.valueOf(arr[0]);
        Student student = studentsRepository.findById(id).get();
        if (arr[1].equals("-")) {
            name = student.getName();
        } else name = arr[1];
        if (arr[2].equals("-")) {
            dob = student.getDob();
        } else dob = transformToDate(arr[2]);
        if (arr[3].equals("-")) {
            faculty = student.getFaculty();
        } else faculty = arr[3];
        if (arr[4].equals("-")) {
            status = student.getStatus();
        } else status = determineStatus(arr[4]);
        studentsRepository.update(id, name, dob, faculty, status);
    }

    private String determineStatus(String string) {
        switch (string) {
            case "g": return "Graduated";
            case "c": return "Current";
            case "e": return "Expelled";
            case "v": return "Student on vacation";
        } return "";
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

}
