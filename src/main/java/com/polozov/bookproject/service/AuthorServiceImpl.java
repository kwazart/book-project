package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.AuthorRepository;
import com.polozov.bookproject.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public Optional<Author> getById(long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Author getByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Author add(String name) {
        return repository.save(new Author(0, name));
    }

    @Transactional
    @Override
    public Author update(long id, String authorName) {
        return repository.save(new Author(id, authorName));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
