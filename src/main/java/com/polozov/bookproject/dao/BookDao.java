package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;

import java.util.List;

public interface BookDao {
    Book findById(long id);
    List<Book> findByBookName(String name);
    List<Book> findByAuthorName(String authorName);
    List<Book> findByGenreName(String genreName);
    List<Book> findAll();
    int insert(Book author);
    int update(Book author);
    int deleteById(long id);
}
