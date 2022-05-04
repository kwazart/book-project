package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DataMongoTest
@DisplayName("Dao для работы с Comment должно")
class CommentRepositoryTest {
    
    private static final long COUNT_OF_ROWS = 3;

    private static final String EXPECTED_BOOK_ID = "10";
    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String EXPECTED_GENRE_NAME = "Детектив";

    @Autowired
    private CommentRepository repository;

    @DisplayName("возвращать ожидаемый объект по книге из БД")
    @Test
    void shouldReturnExpectedObjectByBook() {
        Book book = new Book(EXPECTED_BOOK_ID,
                EXPECTED_BOOK_NAME,
                EXPECTED_AUTHOR_NAME,
                EXPECTED_GENRE_NAME);

        List<Comment> all = repository.findAll();
        int size = (int) all.stream().filter(c -> c.getBook().getId().equals(book.getId())).count();
        assertThat(size).isEqualTo(COUNT_OF_ROWS);
    }
}