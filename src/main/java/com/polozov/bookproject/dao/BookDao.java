package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;

import java.util.List;

public interface BookDao {
    Book findById(long id);
    Book findByBookName(String name);
    List<Book> findByAuthorId(long authorId);
    List<Book> findByGenreId(long genreId);
    List<Book> findAll();
    int insert(Book author);
    int update(Book author);
    int deleteById(long id);
}
