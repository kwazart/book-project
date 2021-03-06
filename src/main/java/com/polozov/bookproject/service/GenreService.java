package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> getById(long id);
    Genre getByName(String name);
    List<Genre> getAll();
    Genre add(String name);
    Genre update(long id, String genreName);
    void deleteById(long id);
}
