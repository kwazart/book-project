package com.polozov.bookproject.mapper;

import com.polozov.bookproject.domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("genre_id");
        String name = rs.getString("genre_name");
        return new Genre(id, name);
    }
}
