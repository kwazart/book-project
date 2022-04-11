package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с Book должно")
@DataJpaTest
class BookRepositoryTest {

    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final long EXPECTED_GENRE_ID = 1;

    private static final long COUNT_OF_AUTHOR_ROWS = 1;
    private static final long COUNT_OF_GENRE_ROWS = 1;

    @Autowired
    private BookRepository repository;

    @DisplayName("возвращать ожидаемый объект по названию книги из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        List<Book> books = repository.findByBookName(EXPECTED_BOOK_NAME);
        assertThat(books.size()).isGreaterThan(0);
        books.forEach(b -> assertThat(b.getName()).isEqualTo(EXPECTED_BOOK_NAME));
    }

    @DisplayName("возвращать ожидаемый объект по имени автора из БД")
    @Test
    void shouldReturnExpectedObjectByAuthorName() {
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        List<Book> books = repository.findByAuthorName(author);
        assertThat(books.size()).isEqualTo(COUNT_OF_AUTHOR_ROWS);
        books.forEach(b -> assertThat(b.getAuthor()).isEqualTo(author));
    }

    @DisplayName("возвращать ожидаемый объект по названию жанра из БД")
    @Test
    void shouldReturnExpectedObjectByGenreName() {
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);
        List<Book> books = repository.findByGenreName(genre);
        assertThat(books.size()).isEqualTo(COUNT_OF_GENRE_ROWS);
        books.forEach(b -> assertThat(b.getGenre()).isEqualTo(genre));
    }
}