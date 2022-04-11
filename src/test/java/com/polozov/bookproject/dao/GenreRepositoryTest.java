package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с Genre должно")
@DataJpaTest
class GenreRepositoryTest {

    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String NEW_GENRE_NAME = "Историческое";
    private static final long EXPECTED_GENRE_ID = 1;
    private static final long COUNT_OF_ROWS = 2;

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