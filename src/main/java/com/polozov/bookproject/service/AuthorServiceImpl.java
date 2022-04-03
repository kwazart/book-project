package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.AuthorDao;
import com.polozov.bookproject.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public Author getById(long id) {
        return dao.findById(id);
    }

    @Override
    public Author getByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Author> getAll() {
        return dao.findAll();
    }

    @Override
    public int add(Author author) {
        return dao.insert(author);
    }

    @Override
    public int update(Author author) {
        return dao.update(author);
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }
}
