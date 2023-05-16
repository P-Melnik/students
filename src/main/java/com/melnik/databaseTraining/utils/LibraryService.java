package com.melnik.databaseTraining.utils;

import com.melnik.databaseTraining.LibraryAccounting;

import java.time.LocalDate;

public class LibraryService {


    public static LibraryAccounting borrowBook(long studentId, long bookId) {
        LibraryAccounting libraryAccounting = new LibraryAccounting(studentId, bookId);
        libraryAccounting.setBorrowDate(LocalDate.now());
        return libraryAccounting;
    }

}
