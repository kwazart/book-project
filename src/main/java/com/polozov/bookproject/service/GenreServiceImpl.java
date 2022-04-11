package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.GenreRepository;
import com.polozov.bookproject.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository dao;

    @Override
    public Optional<Genre> getById(long id) {
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

    @Transactional
    @Override
    public Genre add(String name) {
        return dao.save(new Genre(0, name));
    }

    @Transactional
    @Override
    public int update(long id, String genreName) {
        return dao.update(id, genreName);
    }

    @Transactional
    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }
}
