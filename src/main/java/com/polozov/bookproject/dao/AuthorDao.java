package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author findById(long id);
    Author findByName(String name);
    List<Author> findAll();
    int insert(Author author);
    int update(Author author);
    int deleteById(long id);
}
