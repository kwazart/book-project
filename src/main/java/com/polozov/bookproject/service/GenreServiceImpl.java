package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.GenreDao;
import com.polozov.bookproject.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public Genre getById(long id) {
        return dao.findById(id);
    }

    @Override
    public Genre getByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return dao.findAll();
    }

    @Override
    public int add(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public int update(Genre genre) {
        return dao.update(genre);
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }
}
