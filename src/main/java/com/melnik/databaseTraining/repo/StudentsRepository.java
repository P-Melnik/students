package com.melnik.databaseTraining.repo;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.StudentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    @Query(value = "select * from student where student_status = :status",
    nativeQuery = true)
    public List<Student> selectByStatus(@Param("status") int status);

}