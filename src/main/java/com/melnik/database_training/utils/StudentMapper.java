package com.melnik.database_training.utils;

import com.melnik.database_training.Student;
import com.melnik.database_training.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public static StudentDTO mapToStudentDTO(Student student) {

        return new StudentDTO(student.getId(), student.getFullName(),
                student.getDob(), student.getFaculty(), student.getStudentStatus());
    }
}
