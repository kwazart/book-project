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
                "select b.book_id, b.book_name, a.author_id, a.author_name, g.genre_id, g.genre_name " +
                        "from (books as b " +
                        "left join authors as a on b.author_id = a.author_id) " +
                        "left join genres as g on b.genre_id = g.genre_id " +
                        "where b.book_id = :id",
                Map.of("id", id),
                new BookMapper());
    }

    @Override
    public List<Book> findByBookName(String name) {
        return namedJdbc.query(
                "select b.book_id, b.book_name, a.author_id, a.author_name, g.genre_id, g.genre_name " +
                        "from (books as b " +
                        "left join authors as a on b.author_id = a.author_id) " +
                        "left join genres as g on b.genre_id = g.genre_id " +
                        "where b.book_name = :name",
                Map.of("name", name),
                new BookMapper());
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return namedJdbc.query(
                "select b.book_id, b.book_name, a.author_id, a.author_name, g.genre_id, g.genre_name " +
                        "from (books as b " +
                        "left join authors as a on b.author_id = a.author_id) " +
                        "left join genres as g on b.genre_id = g.genre_id " +
                        "where a.author_name = :authorName",
                Map.of("authorName", authorName),
                new BookMapper());
    }

    @Override
    public List<Book> findByGenreName(String genreName) {
        return namedJdbc.query(
                "select b.book_id, b.book_name, a.author_id, a.author_name, g.genre_id, g.genre_name " +
                        "from (books as b " +
                        "left join authors as a on b.author_id = a.author_id) " +
                        "left join genres as g on b.genre_id = g.genre_id " +
                        "where g.genre_name = :genreName",
                Map.of("genreName", genreName),
                new BookMapper());
    }

    @Override
    public List<Book> findAll() {
        return namedJdbc.query(
                "select b.book_id, b.book_name, a.author_id, a.author_name, g.genre_id, g.genre_name " +
                        "from (books as b " +
                        "left join authors as a on b.author_id = a.author_id) " +
                        "left join genres as g on b.genre_id = g.genre_id",
                new BookMapper());
    }

    @Override
    public int insert(Book book) {
        return namedJdbc.update("insert into books (book_name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                Map.of("name", book.getName(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
    }

    @Override
    public int update(Book book) {
        return namedJdbc.update("update books set book_name = :name, author_id = :author_id, genre_id = :genre_id where book_id = :id",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
    }

    @Override
    public int deleteById(long id) {
        return namedJdbc.update("delete from books where book_id = :id", Map.of("id", id));
    }
}
