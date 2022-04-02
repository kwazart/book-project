package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Book findById(long id) {
        return namedJdbc.queryForObject(
                "select id, name, author_id, genre_id from books where id = :id",
                Map.of("id", id),
                new BookMapper());
    }

    @Override
    public Book findByBookName(String name) {
        return namedJdbc.queryForObject(
                "select id, name, author_id, genre_id from books where name = :name",
                Map.of("name", name),
                new BookMapper());
    }

    @Override
    public List<Book> findByAuthorId(long authorId) {
        return namedJdbc.query(
                "select id, name, author_id, genre_id from books where author_id = :author_id",
                Map.of("author_id", authorId),
                new BookMapper());
    }

    @Override
    public List<Book> findByGenreId(long genreId) {
        return namedJdbc.query(
                "select id, name, author_id, genre_id from books where genre_id = :genre_id",
                Map.of("genre_id", genreId),
                new BookMapper());
    }

    @Override
    public List<Book> findAll() {
        return namedJdbc.getJdbcOperations().query(
                "select id, name, author_id, genre_id from books",
                new BookMapper());
    }

    @Override
    public int insert(Book book) {
        return namedJdbc.update("insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                Map.of("name", book.getName(),
                        "author_id", book.getAuthorId(),
                        "genre_id", book.getGenreId()));
    }

    @Override
    public int update(Book book) {
        return namedJdbc.update("update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "author_id", book.getAuthorId(),
                        "genre_id", book.getGenreId()));
    }

    @Override
    public int deleteById(long id) {
        return namedJdbc.update("delete from books where id = :id", Map.of("id", id));
    }
}
