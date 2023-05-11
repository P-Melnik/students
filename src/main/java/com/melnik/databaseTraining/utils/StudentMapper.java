package com.melnik.databaseTraining.utils;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public static StudentDTO mapToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFullName(student.getFullName());
        studentDTO.setDob(student.getDob());
        studentDTO.setFaculty(student.getFaculty());
        studentDTO.setStudentStatus(student.getStudentStatus());
        return studentDTO;
    }
}
