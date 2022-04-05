package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с Book должно")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final long EXPECTED_GENRE_ID = 1;
    private static final String NEW_BOOK_NAME = "Убийство в Восточном экспрессе";

    private static final long NEW_BOOK_ID = 1;
    private static final long COUNT_OF_ROWS = 2;
    private static final long COUNT_OF_AUTHOR_ROWS = 1;
    private static final long COUNT_OF_GENRE_ROWS = 1;

    @Autowired
    private BookRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        var book = repository.findById(EXPECTED_BOOK_ID);
        var expectedBook = em.find(Book.class, EXPECTED_BOOK_ID);

        assertThat(book).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый объект по названию книги из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        List<Book> books = repository.findByBookName(EXPECTED_BOOK_NAME);
        assertThat(books.size()).isGreaterThan(0);
        books.forEach(b -> assertThat(b.getName()).isEqualTo(EXPECTED_BOOK_NAME));
    }

    @DisplayName("возвращать ожидаемый объект по имени автора из БД")
    @Test
    void shouldReturnExpectedObjectByAuthorName() {
        Author author = new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME);
        List<Book> books = repository.findByAuthorName(author);
        assertThat(books.size()).isEqualTo(COUNT_OF_AUTHOR_ROWS);
        books.forEach(b -> assertThat(b.getAuthor()).isEqualTo(author));
    }

    @DisplayName("возвращать ожидаемый объект по названию жанра из БД")
    @Test
    void shouldReturnExpectedObjectByGenreName() {
        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME);
        List<Book> books = repository.findByGenreName(genre);
        assertThat(books.size()).isEqualTo(COUNT_OF_GENRE_ROWS);
        books.forEach(b -> assertThat(b.getGenre()).isEqualTo(genre));
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void shouldReturnExpectedObjectList() {
        List<Book> books = repository.findAll();
        assertThat(books.size()).isEqualTo(COUNT_OF_ROWS);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void shouldCorrectAddNewObject() {
        Book expectedBook = new Book(
                0,
                NEW_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));

        List<Book> beginList = repository.findAll();
        Book book = repository.save(expectedBook);
        List<Book> endList = repository.findAll();

        Optional<Book> bookOptional = repository.findById(book.getId());

        assertThat(bookOptional).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void shouldCorrectUpdateDbObjectById() {
        var book = repository.findById(EXPECTED_BOOK_ID);
        Book newBook = new Book(
                EXPECTED_BOOK_ID,
                NEW_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));
        assertThat(book).isPresent().get().isNotEqualTo(NEW_BOOK_NAME);

        repository.update(newBook);
        var resultBook = repository.findById(EXPECTED_BOOK_ID);
        assertThat(resultBook).isPresent().get().isNotEqualTo(newBook);
    }

    @DisplayName("удалять объект по id")
    @Test
    void shouldCorrectDeleteObjectFromDbById() {
        Book addedBook = new Book(
                0,
                NEW_BOOK_NAME,
                new Author(EXPECTED_AUTHOR_ID, EXPECTED_AUTHOR_NAME),
                new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_NAME));
        List<Book> beginList = repository.findAll();
        repository.save(addedBook);
        List<Book> midList = repository.findAll();

        assertThat(beginList.size()).isLessThan(midList.size());

        repository.deleteById(addedBook.getId());
        List<Book> endList = repository.findAll();

        assertThat(beginList.size()).isEqualTo(endList.size());
    }
}