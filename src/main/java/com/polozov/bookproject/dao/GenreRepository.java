package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(long id);
    Genre findByName(String name);
    List<Genre> findAll();
    Genre save(Genre genre);
    int update(long id, String name);
    int deleteById(long id);
}
