package com.melnik.databaseTraining.repo;

import com.melnik.databaseTraining.Student;
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

    @Query(value = "select * from student where student_status = 'Graduated'",
            nativeQuery = true)
    public List<Student> selectGraduated();

    @Query(value = "select * from student where student_status = 'Expelled'",
            nativeQuery = true)
    public List<Student> selectExpelled();

    @Query(value = "select * from student where student_status = 'Student on vacation'",
            nativeQuery = true)
    public List<Student> selectVacation();

    @Query(value = "select * from student where student_status = 'Current'",
            nativeQuery = true)
    public List<Student> selectCurrent();

    @Transactional
    @Modifying
    @Query(value = "update student set name =:name, dob =:dob, faculty =:faculty," +
            " student_status =:status " +
            "where id =:id",
    nativeQuery = true)
    public void update(@Param("id") long id, @Param("name") String name,
                          @Param("dob") LocalDate dob, @Param("faculty") String faculty,
                          @Param("status") String status);
}
