package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Genre findById(long id) {
        return namedJdbc.queryForObject(
                "select genre_id, genre_name from genres where genre_id = :id",
                Map.of("id", id),
                new GenreMapper());
    }

    @Override
    public Genre findByName(String name) {
        return namedJdbc.queryForObject(
                "select genre_id, genre_name from genres where genre_name = :name",
                Map.of("name", name),
                new GenreMapper());
    }

    @Override
    public List<Genre> findAll() {
        return namedJdbc.getJdbcOperations().query(
                "select genre_id, genre_name from genres",
                new GenreMapper());
    }

    @Override
    public int insert(Genre genre) {
        return namedJdbc.update("insert into genres (genre_name) values (:name)",
                Map.of("name", genre.getName()));
    }

    @Override
    public int update(Genre genre) {
        return namedJdbc.update("update genres set genre_name = :name where genre_id = :id",
                Map.of("id", genre.getId(),
                        "name", genre.getName()));
    }

    @Override
    public int deleteById(long id) {
        return namedJdbc.update("delete from genres where genre_id = :id", Map.of("id", id));
    }
}
