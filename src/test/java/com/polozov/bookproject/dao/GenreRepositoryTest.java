package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с Genre должно")
class GenreRepositoryTest extends AbstractRepositoryTest {

    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String EXPECTED_GENRE_ID = "10";

    @Autowired
    private GenreRepository repository;

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Genre expectedGenre = repository.findByName(EXPECTED_GENRE_NAME);
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);

        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(genre);
    }
}