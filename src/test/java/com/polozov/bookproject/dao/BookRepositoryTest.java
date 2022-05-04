package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Dao для работы с Book должно")
class BookRepositoryTest {

    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String EXPECTED_AUTHOR_ID = "10";
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String EXPECTED_GENRE_ID = "10";

    private static final long COUNT_OF_AUTHOR_ROWS = 1;
    private static final long COUNT_OF_GENRE_ROWS = 1;

    @Autowired
    private BookRepository repository;

    @DisplayName("возвращать ожидаемый объект по названию книги из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        List<Book> books = repository.findAllByName(EXPECTED_BOOK_NAME);
        assertThat(books.size()).isGreaterThan(0);
        books.forEach(b -> assertThat(b.getName()).isEqualTo(EXPECTED_BOOK_NAME));
    }

    @DisplayName("возвращать ожидаемый объект по имени автора из БД")
    @Test
    void shouldReturnExpectedObjectByAuthorName() {
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        List<Book> books = repository.findAllByAuthor(EXPECTED_AUTHOR_NAME);

        assertThat(books.size()).isEqualTo(COUNT_OF_AUTHOR_ROWS);
        books.forEach(b -> assertThat(b.getAuthor()).isEqualTo(author.getName()));
    }

    @DisplayName("возвращать ожидаемый объект по названию жанра из БД")
    @Test
    void shouldReturnExpectedObjectByGenreName() {
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);
        List<Book> books = repository.findAllByGenre(EXPECTED_GENRE_NAME);

        assertThat(books.size()).isEqualTo(COUNT_OF_GENRE_ROWS);
        books.forEach(b -> assertThat(b.getGenre()).isEqualTo(genre.getName()));
    }
}