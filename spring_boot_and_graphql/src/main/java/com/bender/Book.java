package com.bender;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Book(
        Integer id,
        String name,
        Integer pageCount,
        Integer authorId
) {

    public static List<Book> books = Arrays.asList(
            new Book(1, "Book1", 100, 1),
            new Book(2, "Book2", 200, 2),
            new Book(3, "Book3", 300,3)
    );

    public static Optional<Book> bookById(Integer id) {
        return books.stream()
                .filter(book -> book.id.equals(id))
                .findFirst();
    }
}
