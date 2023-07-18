package com.melnik.database_training;

import java.time.LocalDate;

public record StudentDTO (long id, String fullName, LocalDate dob,
                          String faculty, StudentStatus studentStatus) {}
