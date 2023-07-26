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
            "values (:bookId, :studentId, :date) on conflict (book_id, student_id) " +
            "do update set borrow_date = :date, return_date = null " +
            "where library_accounting.book_id =:bookId and " +
            "library_accounting.student_id = :studentId " +
            "and library_accounting.return_date is not null",
    nativeQuery = true)
    int borrowBook(@Param("studentId") long studentId, @Param("bookId") long bookId,
                   LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "update library_accounting set return_date = :date where " +
            "book_id = :bookId and student_id = :studentId and return_date is null",
    nativeQuery = true)
    int returnBook(@Param("studentId") long studentId, @Param("bookId") long bookId,
                   LocalDate date);

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
