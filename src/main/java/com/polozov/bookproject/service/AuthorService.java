package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);
    Author getByName(String name);
    List<Author> getAll();
    int add(Author author);
    int update(Author author);
    int deleteById(long id);
}
