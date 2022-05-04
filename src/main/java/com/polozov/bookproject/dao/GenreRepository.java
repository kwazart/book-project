package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String name);
}
