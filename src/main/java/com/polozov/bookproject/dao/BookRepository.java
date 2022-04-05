package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(long id);
    List<Book> findByBookName(String name);
    List<Book> findByAuthorName(Author author);
    List<Book> findByGenreName(Genre genre);
    List<Book> findAll();
    Book save(Book book);
    int update(Book book);
    int deleteById(long id);
}
