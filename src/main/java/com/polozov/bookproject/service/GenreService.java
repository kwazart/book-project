package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);
    Genre getByName(String name);
    List<Genre> getAll();
    int add(Genre genre);
    int update(Genre genre);
    int deleteById(long id);
}
