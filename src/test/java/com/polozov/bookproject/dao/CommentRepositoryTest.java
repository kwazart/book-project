package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("Dao для работы с Comment должно")
@DataJpaTest
class CommentRepositoryTest {
    
    private static final long COUNT_OF_ROWS = 2;

    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final long EXPECTED_GENRE_ID = 1;

    @Autowired
    private CommentRepository repository;

    @DisplayName("возвращать ожидаемый объект по книге из БД")
    @Test
    void shouldReturnExpectedObjectByBook() {
        Book book = new Book(EXPECTED_BOOK_ID,
                EXPECTED_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));

        List<Comment> comments = repository.findByBook(book);
        assertThat(comments.size()).isEqualTo(COUNT_OF_ROWS);
    }
}