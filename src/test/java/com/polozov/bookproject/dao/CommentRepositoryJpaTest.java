package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Dao для работы с Comment должно")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    private static final long EXPECTED_COMMENT_ID = 1;
    private static final String NEW_COMMENT_TEXT = "Пример коментария - 3";
    private static final long COUNT_OF_ROWS = 2;

    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final long EXPECTED_GENRE_ID = 1;

    @Autowired
    private CommentRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        var comment = repository.findById(EXPECTED_COMMENT_ID);
        var expectedComment = em.find(Comment.class, EXPECTED_COMMENT_ID);
        assertThat(comment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

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


    @DisplayName("добавлять корректно новый объект")
    @Test
    void shouldCorrectAddNewObject() {
        Book book = new Book(EXPECTED_BOOK_ID,
                EXPECTED_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));

        var expectedComment = new Comment(0, NEW_COMMENT_TEXT, book);
        List<Comment> beginList = repository.findByBook(book);
        repository.save(expectedComment);
        List<Comment> endList = repository.findByBook(book);

        var foundCommentOptional = repository.findById(expectedComment.getId());
        assertThat(foundCommentOptional).isPresent().get().isEqualTo(expectedComment);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void shouldCorrectUpdateDbObjectById() {
        Book book = new Book(EXPECTED_BOOK_ID,
                EXPECTED_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));

        var comment = repository.findById(EXPECTED_COMMENT_ID);
        var newComment = new Comment(EXPECTED_COMMENT_ID, NEW_COMMENT_TEXT, book);
        assertThat(comment).isPresent().get().isNotEqualTo(newComment);

        repository.update(EXPECTED_COMMENT_ID, NEW_COMMENT_TEXT, book);
        var resultComment = repository.findById(EXPECTED_COMMENT_ID);
        assertThat(resultComment).isPresent().get().isNotEqualTo(newComment);
    }

    @DisplayName("удалять объект по id")
    @Test
    void shouldCorrectDeleteObjectFromDbById() {
        Book book = new Book(EXPECTED_BOOK_ID,
                EXPECTED_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));

        List<Comment> beginList = repository.findByBook(book);
        repository.deleteById(EXPECTED_COMMENT_ID);
        List<Comment> endList = repository.findByBook(book);

        assertThat(beginList.size()).isEqualTo(endList.size() + 1);

        endList.forEach(c -> assertThat(c.getId()).isNotEqualTo(EXPECTED_COMMENT_ID));

    }
}