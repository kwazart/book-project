package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Author findById(long id) {
        return namedJdbc.queryForObject(
                "select id, name from authors where id = :id",
                Map.of("id", id),
                new AuthorMapper());
    }

    @Override
    public Author findByName(String name) {
        return namedJdbc.queryForObject(
                "select id, name from authors where name = :name",
                Map.of("name", name),
                new AuthorMapper());
    }

    @Override
    public List<Author> findAll() {
        return namedJdbc.getJdbcOperations().query(
                "select id, name from authors",
                new AuthorMapper());
    }

    @Override
    public int insert(Author author) {
        return namedJdbc.update("insert into authors (name) values (:name)",
                Map.of("name", author.getName()));

    }

    @Override
    public int update(Author author) {
        return namedJdbc.update("update authors set name = :name where id = :id",
                Map.of("id", author.getId(),
                        "name", author.getName()));
    }

    @Override
    public int deleteById(long id) {
        return namedJdbc.update("delete from authors where id = :id", Map.of("id", id));
    }
}
