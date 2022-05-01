package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("Dao для работы с Comment должно")
class CommentRepositoryTest extends AbstractRepositoryTest {
    
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

        List<Comment> comments = repository.findAllByBookId(book.getId());
        assertThat(comments.size()).isEqualTo(COUNT_OF_ROWS);
    }
}