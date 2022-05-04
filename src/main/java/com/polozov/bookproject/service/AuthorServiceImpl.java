package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.AuthorRepository;
import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.exception.ObjectNotFoundException;
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
    public Optional<Author> getById(String id) {
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
        return repository.save(new Author(name));
    }

    @Transactional
    @Override
    public Author update(String id, String newAuthorName) {
        Optional<Author> authorOptional = repository.findById(id);
        if (authorOptional.isEmpty()) {
            throw new ObjectNotFoundException("Incorrect author id");
        }
        Author author = authorOptional.get();
        author.setName(newAuthorName);
        return repository.save(author);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
