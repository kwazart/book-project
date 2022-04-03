package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre findById(long id);
    Genre findByName(String name);
    List<Genre> findAll();
    int insert(Genre author);
    int update(Genre author);
    int deleteById(long id);
}
