package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByName(String name);
}
