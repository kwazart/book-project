package com.polozov.bookproject.mapper;

import com.polozov.bookproject.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        long authorId = rs.getLong("author_id");
        long genreId = rs.getLong("genre_id");
        return new Book(id, name, authorId, genreId);
    }
}
