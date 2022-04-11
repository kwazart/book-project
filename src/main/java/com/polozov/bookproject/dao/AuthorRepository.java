package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(long id);
    Author findByName(String name);
    List<Author> findAll();
    Author save(Author author);
    int update(long id, String name);
    int deleteById(long id);
}
