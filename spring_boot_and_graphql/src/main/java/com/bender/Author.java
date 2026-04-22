package com.bender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Author(Integer id, String name) {

    public static List<Author> authors = Arrays.asList(
            new Author(1, "Author 1"),
            new Author(2, "Author 2"),
            new Author(3, "Author 3")
    );

    public static Optional<Author> authorById(Integer id) {
        return authors.stream()
                .filter(author -> author.id.equals(id))
                .findFirst();
    }
}
