package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с Book должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long EXPECTED_GENRE_ID = 1;
    private static final String NEW_BOOK_NAME = "Убийство в Восточном экспрессе";
    private static final long EXPECTED_BOOK_ID = 1;
    private static final long NEW_BOOK_ID = 1;

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        Book book = dao.findById(1);
        assertThat(book.getId()).isEqualTo(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по названию книги из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Book book = dao.findByBookName(EXPECTED_BOOK_NAME);
        assertThat(book.getId()).isEqualTo(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по id автора из БД")
    @Test
    void shouldReturnExpectedObjectByAuthorName() {
        List<Book> books = dao.findByAuthorId(EXPECTED_AUTHOR_ID);
        Book book = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        assertThat(books).containsExactlyInAnyOrder(book);
    }

    @DisplayName("возвращать ожидаемый объект по id жанра из БД")
    @Test
    void shouldReturnExpectedObjectByGenreName() {
        List<Book> books = dao.findByAuthorId(EXPECTED_GENRE_ID);
        Book book = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        assertThat(books).containsExactlyInAnyOrder(book);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void findAll() {
        Book book = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        List<Book> books = dao.findAll();
        assertThat(books).containsExactlyInAnyOrder(book);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void insert() {
        Book expectedBook = new Book(NEW_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);

        List<Book> beginList = dao.findAll();
        dao.insert(expectedBook);
        List<Book> endList = dao.findAll();

        Book foundBook = dao.findById(NEW_BOOK_ID);
        assertThat(expectedBook).isEqualTo(foundBook);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void update() {
        Book book = dao.findById(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isNotEqualTo(NEW_BOOK_NAME);
        Book newBook = new Book(EXPECTED_BOOK_ID, NEW_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        dao.update(newBook);
        Book resultBook = dao.findById(EXPECTED_BOOK_ID);
        assertThat(resultBook).isEqualTo(newBook);
    }

    @DisplayName("удалять объект по id")
    @Test
    void deleteById() {
        Book addedBook = new Book(0, NEW_BOOK_NAME, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        dao.insert(addedBook);
        Book foundBook = dao.findByBookName(NEW_BOOK_NAME);
        assertThat(addedBook.getName()).isEqualTo(foundBook.getName());
        assertThat(addedBook.getAuthorId()).isEqualTo(foundBook.getAuthorId());
        assertThat(addedBook.getGenreId()).isEqualTo(foundBook.getGenreId());

        dao.deleteById(foundBook.getId());
        assertThatThrownBy(() -> dao.findById(foundBook.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}