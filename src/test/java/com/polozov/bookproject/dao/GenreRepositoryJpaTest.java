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
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String NEW_GENRE_NAME = "Историческое";
    private static final long EXPECTED_GENRE_ID = 1;
    private static final long COUNT_OF_ROWS = 2;

    @Autowired
    private GenreRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        var genre = repository.findById(EXPECTED_GENRE_ID);
        var expectedGenre = em.find(Genre.class, EXPECTED_GENRE_ID);

        assertThat(genre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Genre expectedGenre = repository.findByName(EXPECTED_GENRE_NAME);
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);

        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(genre);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void findAll() {
        List<Genre> genres = repository.findAll();
        assertThat(genres.size()).isEqualTo(COUNT_OF_ROWS);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void shouldCorrectAddNewObject() {
        Genre expectedGenre = new Genre(0, NEW_GENRE_NAME);

        List<Genre> beginList = repository.findAll();
        Genre genre =  repository.save(expectedGenre);
        List<Genre> endList = repository.findAll();

        var foundGenreOptional = repository.findById(genre.getId());
        assertThat(foundGenreOptional).isPresent().get().usingRecursiveComparison().isEqualTo(genre);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void shouldCorrectUpdateDbObjectById() {
        var genre = repository.findById(EXPECTED_GENRE_ID);
        Genre newGenre = new Genre(EXPECTED_GENRE_ID, NEW_GENRE_NAME);
        assertThat(genre).isPresent().get().isNotEqualTo(newGenre);


        repository.update(newGenre.getId(), newGenre.getName());
        var resultGenre = repository.findById(EXPECTED_GENRE_ID);
        assertThat(resultGenre).isPresent().get().isNotEqualTo(newGenre);
    }

    @DisplayName("удалять объект по id")
    @Test
    void shouldCorrectDeleteObjectFromDbById() {
        Genre addedGenre = new Genre(0, NEW_GENRE_NAME);
        List<Genre> beginList = repository.findAll();
        repository.save(addedGenre);
        List<Genre> midList = repository.findAll();

        assertThat(beginList.size()).isLessThan(midList.size());

        repository.deleteById(addedGenre.getId());
        List<Genre> endList = repository.findAll();

        assertThat(beginList.size()).isEqualTo(endList.size());
    }
}