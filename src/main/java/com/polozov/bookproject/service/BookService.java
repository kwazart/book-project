package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Book;

import java.util.List;

public interface BookService {
    Book getById(long id);
    Book getByName(String name);
    List<Book> getByAuthorName(String name);
    List<Book> getByGenreName(String name);
    List<Book> getAll();
    int add(Book book);
    int update(Book book);
    int deleteById(long id);
}
