package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getById(String id);
    List<Book> getByName(String name);
    List<Book> getByAuthorName(String name);
    List<Book> getByGenreName(String name);
    List<Book> getAll();
    Book add(String bookName, String authorName, String genreName);
    Book update(String id, String bookName, String authorName, String genreName);
    void deleteById(String id);
    void deleteByAuthor(String authorName);
    void deleteByGenre(String genreName);
    void updateAllAuthors(String oldAuthorName, String newAuthorName);
    void updateAllGenres(String oldGenreName, String newGenreName);
}
