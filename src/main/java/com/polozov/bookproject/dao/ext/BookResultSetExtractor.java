package com.polozov.bookproject.dao.ext;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BookResultSetExtractor implements ResultSetExtractor<Map<Long, Book>> {
    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Book> books = new HashMap<>();

        while (rs.next()) {
            long id = rs.getLong("book_id");
            Book book = books.get(id);
            if (book == null) {
                book =  new Book(id, rs.getString("book_name"),
                        new Author(rs.getLong("author_id"), rs.getString("author_name")),
                        new Genre(rs.getLong("genre_id"), rs.getString("genre_name")));
                books.put(book.getId(), book);
            }
        }
        return books;
    }
}
