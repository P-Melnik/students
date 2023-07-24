package com.melnik.database_training.repo;

import com.melnik.database_training.LibraryAccounting;
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
    @Query(value = "insert into library_accounting (book_id, student_id, borrow_date) " +
            "values (:bookId, :studentId, :date)",
    nativeQuery = true)
    void borrowBook(@Param("studentId") long studentId, @Param("bookId") long bookId,
                   LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "delete from library_accounting where student_id = :studentId AND" +
            " book_id = :bookId",
    nativeQuery = true)
    int returnBook(@Param("studentId") long studentId, @Param("bookId") long bookId);

    @Query (value = "select * from library_accounting where return_date is null",
    nativeQuery = true)
    List<LibraryAccounting> borrowed();

    @Query(value = "select * from library_accounting where student_id = :studentId",
    nativeQuery = true)
    List<LibraryAccounting> borrowedByStudent(@Param("studentId") long id);

    @Query(value = "select id from library_accounting where student_id = :studentId " +
            "AND book_id = :bookId AND return_date is null",
    nativeQuery = true)
    long getLibraryId(@Param("studentId") long studentId, @Param("bookId") long bookId);

    @Query(value = "select id from library_accounting where student_id = :studentId " +
            "AND book_id = :bookId AND return_date is null",
    nativeQuery = true)
    Long bookIsBorrowedByThisStudent(@Param("studentId") long studentId, @Param("bookId") long bookId);
}
