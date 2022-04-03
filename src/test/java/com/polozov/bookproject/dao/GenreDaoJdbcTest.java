package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с Genre должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String NEW_GENRE_NAME = "Историческое";
    private static final long EXPECTED_GENRE_ID = 1;

    @Autowired
    private GenreDaoJdbc dao;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        Genre genre = dao.findById(1);
        assertThat(genre.getId()).isEqualTo(EXPECTED_GENRE_ID);
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Genre genre = dao.findByName(EXPECTED_GENRE_NAME);
        assertThat(genre.getId()).isEqualTo(EXPECTED_GENRE_ID);
        assertThat(genre.getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void findAll() {
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);
        List<Genre> genres = dao.findAll();
        assertThat(genres).containsExactlyInAnyOrder(genre);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void insert() {
        Genre expectedGenre = new Genre(0, EXPECTED_GENRE_NAME);

        List<Genre> beginList = dao.findAll();
        dao.insert(expectedGenre);
        List<Genre> endList = dao.findAll();

        Genre foundGenre = dao.findById(EXPECTED_GENRE_ID);
        assertThat(expectedGenre.getName()).isEqualTo(foundGenre.getName());
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void update() {
        Genre genre = dao.findById(EXPECTED_GENRE_ID);
        assertThat(genre.getName()).isNotEqualTo(NEW_GENRE_NAME);
        Genre newGenre = new Genre(EXPECTED_GENRE_ID, NEW_GENRE_NAME);
        dao.update(newGenre);
        Genre resultGenre = dao.findById(EXPECTED_GENRE_ID);
        assertThat(resultGenre).isEqualTo(newGenre);
    }

    @DisplayName("удалять объект по id")
    @Test
    void deleteById() {
        Genre addedGenre = new Genre(0, NEW_GENRE_NAME);
        dao.insert(addedGenre);
        Genre foundGenre = dao.findByName(NEW_GENRE_NAME);
        assertThat(addedGenre.getName()).isEqualTo(foundGenre.getName());

        dao.deleteById(foundGenre.getId());
        assertThatThrownBy(() -> dao.findById(foundGenre.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);

    }
}