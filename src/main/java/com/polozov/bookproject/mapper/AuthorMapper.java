package com.polozov.bookproject.mapper;

import com.polozov.bookproject.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("author_id");
        String name = rs.getString("author_name");
        return new Author(id, name);
    }
}
