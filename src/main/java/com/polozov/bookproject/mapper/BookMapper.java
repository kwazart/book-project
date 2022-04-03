package com.polozov.bookproject.mapper;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("book_id");
        String name = rs.getString("book_name");
        long authorId = rs.getLong("author_id");
        String authorName = rs.getString("author_name");
        long genreId = rs.getLong("genre_id");
        String genreName = rs.getString("genre_name");
        return new Book(id, name, new Author(authorId, authorName), new Genre(genreId, genreName));
    }
}
