package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с Author должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String NEW_AUTHOR_NAME = "Говард Лавкрафт";
    private static final long EXPECTED_AUTHOR_ID = 1;

    @Autowired
    private AuthorDaoJdbc dao;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        Author author = dao.findById(1);
        assertThat(author.getId()).isEqualTo(EXPECTED_AUTHOR_ID);
        assertThat(author.getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Author author = dao.findByName(EXPECTED_AUTHOR_NAME);
        assertThat(author.getId()).isEqualTo(EXPECTED_AUTHOR_ID);
        assertThat(author.getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void findAll() {
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        List<Author> authors = dao.findAll();
        assertThat(authors).containsExactlyInAnyOrder(author);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void insert() {
        Author expectedAuthor = new Author(0, EXPECTED_AUTHOR_NAME);

        List<Author> beginList = dao.findAll();
        dao.insert(expectedAuthor);
        List<Author> endList = dao.findAll();

        Author foundAuthor = dao.findById(EXPECTED_AUTHOR_ID);
        assertThat(expectedAuthor.getName()).isEqualTo(foundAuthor.getName());
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void update() {
        Author author = dao.findById(EXPECTED_AUTHOR_ID);
        assertThat(author.getName()).isNotEqualTo(NEW_AUTHOR_NAME);
        Author newAuthor = new Author(EXPECTED_AUTHOR_ID, NEW_AUTHOR_NAME);
        dao.update(newAuthor);
        Author resultAuthor = dao.findById(EXPECTED_AUTHOR_ID);
        assertThat(resultAuthor).isEqualTo(newAuthor);
    }

    @DisplayName("удалять объект по id")
    @Test
    void deleteById() {
        Author addedAuthor = new Author(0, NEW_AUTHOR_NAME);
        dao.insert(addedAuthor);
        Author foundAuthor = dao.findByName(NEW_AUTHOR_NAME);
        assertThat(addedAuthor.getName()).isEqualTo(foundAuthor.getName());

        dao.deleteById(foundAuthor.getId());
        assertThatThrownBy(() -> dao.findById(foundAuthor.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}