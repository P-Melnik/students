package com.melnik.databaseTraining.repo;

import com.melnik.databaseTraining.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying
    @Query(value = "update book set name = :#{#book.getBookName}, amount = :#{#book.getAmount} ," +
            "where id = :id",
    nativeQuery = true)
    void update(@Param("id") long id, Book book);
}
