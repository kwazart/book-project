package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.GenreRepository;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public Optional<Genre> getById(String id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Genre getByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Genre add(String name) {
        return repository.save(new Genre(name));
    }

    @Transactional
    @Override
    public Genre update(String id, String newGenreName) {
        Optional<Genre> genreOptional = repository.findById(id);
        if (genreOptional.isEmpty()) {
            throw new ObjectNotFoundException("Incorrect genre id ");
        }
        Genre genre = genreOptional.get();


        genre.setName(newGenreName);
        return repository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
