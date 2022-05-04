package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String authorName);
    List<Book> findAllByGenre(String genre);
    void deleteAllByAuthor(String authorName);
    void deleteAllByGenre(String genreName);
}
