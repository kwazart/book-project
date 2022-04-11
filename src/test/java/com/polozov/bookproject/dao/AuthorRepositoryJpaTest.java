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
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String NEW_AUTHOR_NAME = "Говард Лавкрафт";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long COUNT_OF_ROWS = 2;

    @Autowired
    private AuthorRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        var author = repository.findById(EXPECTED_AUTHOR_ID);
        var expectedAuthor = em.find(Author.class, EXPECTED_AUTHOR_ID);

        assertThat(author).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый объект по name из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        Author expectedAuthor = repository.findByName(EXPECTED_AUTHOR_NAME);
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);

        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(author);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void shouldReturnExpectedObjectList() {
        List<Author> authors = repository.findAll();
        assertThat(authors.size()).isEqualTo(COUNT_OF_ROWS);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void shouldCorrectAddNewObject() {
        Author expectedAuthor = new Author(0, NEW_AUTHOR_NAME);

        List<Author> beginList = repository.findAll();
        repository.save(expectedAuthor);
        List<Author> endList = repository.findAll();

        var foundAuthorOptional = repository.findById(expectedAuthor.getId());
        assertThat(foundAuthorOptional).isPresent().get().isEqualTo(expectedAuthor);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void shouldCorrectUpdateDbObjectById() {
        var author = repository.findById(EXPECTED_AUTHOR_ID);
        Author newAuthor = new Author(EXPECTED_AUTHOR_ID, NEW_AUTHOR_NAME);
        assertThat(author).isPresent().get().isNotEqualTo(newAuthor);


        repository.update(EXPECTED_AUTHOR_ID, newAuthor.getName());
        var resultAuthor = repository.findById(EXPECTED_AUTHOR_ID);
        assertThat(resultAuthor).isPresent().get().isNotEqualTo(newAuthor);
    }

    @DisplayName("удалять объект по id")
    @Test
    void shouldCorrectDeleteObjectFromDbById() {
        Author addedAuthor = new Author(0, NEW_AUTHOR_NAME);
        List<Author> beginList = repository.findAll();
        repository.save(addedAuthor);
        List<Author> midList = repository.findAll();

        assertThat(beginList.size()).isLessThan(midList.size());

        repository.deleteById(addedAuthor.getId());
        List<Author> endList = repository.findAll();

        assertThat(beginList.size()).isEqualTo(endList.size());
    }
}