package com.bender.student;

import com.bender.book.Book;
import com.bender.courseenrollment.CourseEnrollment;

import java.time.Instant;
import java.util.Set;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        Set<Book> books,
        Set<CourseEnrollment> courseEnrollments,
        Instant createdAt
) {
}
