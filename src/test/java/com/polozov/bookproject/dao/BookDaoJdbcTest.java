package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с Book должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final String EXPECTED_BOOK_NAME = "Пуаро";
    private static final String EXPECTED_AUTHOR_NAME = "Агата Кристи";
    private static final String EXPECTED_GENRE_NAME = "Детектив";
    private static final String NEW_BOOK_NAME = "Убийство в Восточном экспрессе";
    private static final long EXPECTED_BOOK_ID = 1;
    private static final long NEW_BOOK_ID = 1;

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("возвращать ожидаемый объект по id из БД")
    @Test
    void shouldReturnExpectedObjectById() {
        Book book = dao.findById(1);
        assertThat(book.getId()).isEqualTo(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
        assertThat(book.getAuthor().getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
        assertThat(book.getGenre().getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по названию книги из БД")
    @Test
    void shouldReturnExpectedObjectByName() {
        List<Book> books = dao.findByBookName(EXPECTED_BOOK_NAME);
        books.forEach(b -> assertThat(b.getName()).isEqualTo(EXPECTED_BOOK_NAME));
    }

    @DisplayName("возвращать ожидаемый объект по имени автора из БД")
    @Test
    void shouldReturnExpectedObjectByAuthorName() {
        List<Book> books = dao.findByAuthorName(EXPECTED_AUTHOR_NAME);
        Book book = books.get(0);
        assertThat(book.getId()).isEqualTo(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
        assertThat(book.getAuthor().getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
        assertThat(book.getGenre().getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый объект по названию жанра из БД")
    @Test
    void shouldReturnExpectedObjectByGenreName() {
        List<Book> books = dao.findByGenreName(EXPECTED_GENRE_NAME);
        Book book = books.get(0);
        assertThat(book.getId()).isEqualTo(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isEqualTo(EXPECTED_BOOK_NAME);
        assertThat(book.getAuthor().getName()).isEqualTo(EXPECTED_AUTHOR_NAME);
        assertThat(book.getGenre().getName()).isEqualTo(EXPECTED_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый список объектов из БД")
    @Test
    void findAll() {
        List<Book> books = dao.findAll();
        assertThat(books.size()).isEqualTo(1);
    }

    @DisplayName("добавлять корректно новый объект")
    @Test
    void insert() {
        Book book = dao.findById(EXPECTED_BOOK_ID);

        Book expectedBook = new Book(
                NEW_BOOK_ID,
                EXPECTED_BOOK_NAME,
                book.getAuthor(),
                book.getGenre());

        List<Book> beginList = dao.findAll();
        dao.insert(expectedBook);
        List<Book> endList = dao.findAll();

        Book foundBook = dao.findById(NEW_BOOK_ID);
        assertThat(expectedBook).isEqualTo(foundBook);
        assertThat(beginList.size() + 1).isEqualTo(endList.size());
    }

    @DisplayName("обновлять существующий объект по id, передавая измененный объект")
    @Test
    void update() {
        Book book = dao.findById(EXPECTED_BOOK_ID);
        assertThat(book.getName()).isNotEqualTo(NEW_BOOK_NAME);
        Book newBook = new Book(EXPECTED_BOOK_ID, NEW_BOOK_NAME, book.getAuthor(), book.getGenre());
        dao.update(newBook);
        Book resultBook = dao.findById(EXPECTED_BOOK_ID);
        assertThat(resultBook).isEqualTo(newBook);
        assertThat(resultBook).isNotEqualTo(book);
    }

    @DisplayName("удалять объект по id")
    @Test
    void deleteById() {
        Book book = dao.findById(EXPECTED_BOOK_ID);
        Book addedBook = new Book(0, NEW_BOOK_NAME, book.getAuthor(), book.getGenre());
        dao.insert(addedBook);
        int startCount = dao.findAll().size();
        dao.deleteById(EXPECTED_BOOK_ID);
        int endCount = dao.findAll().size();
        assertThat(startCount).isEqualTo(endCount + 1);
    }
}