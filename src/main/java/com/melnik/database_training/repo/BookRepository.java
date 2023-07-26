package com.melnik.database_training.repo;

import com.melnik.database_training.Book;
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
    @Query(value = "update book set title = :#{#book.getTitle}, amount = :#{#book.getAmount} " +
            "where id = :id",
    nativeQuery = true)
    int update(@Param("id") long id, Book book);

    @Transactional
    @Modifying
    @Query(value = "delete from book where id = :id",
            nativeQuery = true)
    int delete(@Param("id") long id);

//    @Query(value = "select balance_amount from book where id = :id",
//    nativeQuery = true)
//    int amount(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update book set balance_amount = balance_amount - 1 where id = :id",
    nativeQuery = true)
    void balanceMinus(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update book set balance_amount = balance_amount + 1 where id = :id",
            nativeQuery = true)
    void balancePlus(@Param("id") long id);

}
