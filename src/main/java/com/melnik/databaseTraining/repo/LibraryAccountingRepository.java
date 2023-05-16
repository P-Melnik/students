package com.melnik.databaseTraining.repo;

import com.melnik.databaseTraining.LibraryAccounting;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibraryAccountingRepository extends JpaRepository<LibraryAccounting, Long> {

    @Transactional
    @Modifying
    @Query(value = "update library_accounting set return_date = :date where id = :id",
    nativeQuery = true)
    void update(@Param("id") long id, LocalDate date);

    @Query (value = "select * from library_accounting where return_date is null",
    nativeQuery = true)
    List<LibraryAccounting> borrowed();

    @Query(value = "select * from library_accounting where student_id = :studentId",
    nativeQuery = true)
    List<LibraryAccounting> borrowedByStudent(@Param("studentId") long id);
}
