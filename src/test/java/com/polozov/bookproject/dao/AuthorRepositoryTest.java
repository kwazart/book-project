package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Dao для работы с Author должно")
class AuthorRepositoryTest {

    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String EXPECTED_AUTHOR_ID = "10";

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