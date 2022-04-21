package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с Author должно")
@DataJpaTest
class AuthorRepositoryTest {

    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String NEW_AUTHOR_NAME = "Говард Лавкрафт";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long COUNT_OF_ROWS = 2;

    @Autowired
    private AuthorRepository repository;

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Author expectedAuthor = repository.findByName(EXPECTED_AUTHOR_NAME);
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);

        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(author);
    }
}