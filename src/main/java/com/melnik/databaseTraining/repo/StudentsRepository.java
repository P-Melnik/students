package com.melnik.databaseTraining.repo;

import com.melnik.databaseTraining.Student;
import com.melnik.databaseTraining.StudentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    @Query(value = "select * from student where student_status = :#{#studentStatus.getNum()}",
            nativeQuery = true)
    List<Student> list(@Param("studentStatus") StudentStatus studentStatus);

    @Transactional // we get TransactionException without this annotation
    @Modifying
    @Query(value = "update student set full_name = :#{#student.getFullName()}, dob = :#{#student.getDob()}, " +
            "faculty = :#{#student.getFaculty()}, student_status = :#{#student.getStudentStatus().getNum()} " +
            "where id = :id",
            nativeQuery = true)
    void update(@Param("id") long id, Student student);


}
