package com.bender.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select " +
            "new com.bender.book.BookDto(b.id, b.title, b.createdAt) " +
            "from Book b")
    List<BookDto> getAllBooksDto();

    @Query("select " +
            "new com.bender.book.BookDto(b.id, b.title, b.createdAt) " +
            "from Book b " +
            "where b.id = ?1")
    Optional<BookDto> getAllBookDtoById(Long id);
}
