package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> getById(long id);
    Author getByName(String name);
    List<Author> getAll();
    Author add(String name);
    Author update(long id, String authorName);
    void deleteById(long id);
}
