package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.service.BookService;
import com.polozov.bookproject.util.DataPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@ShellComponent
public class BookShell {

    private final BookService service;
    private final DataPrinter printer;
    private static final String STRING_ROW_TEMPLATE = "%d - %s - %s - %s";

    @ShellMethod(value = "Find book by id", key = {"find-book-id", "fbi"})
    public void findBookById(@ShellOption long id) {
        Optional<Book> optionalBook = service.getById(id);
        optionalBook.ifPresent(book -> printer.printLine(convertObjectStringView(book)));
    }

    @ShellMethod(value = "Find books by name", key = {"find-book-name", "fbn"})
    public void findBookByName(@ShellOption String name) {
        service.getByName(name).forEach(b -> printer.printLine(convertObjectStringView(b)));
    }

    @ShellMethod(value = "Find book by author name", key = {"find-book-author", "fba"})
    public void findBookByAuthorName(@ShellOption String authorName) {
        service.getByAuthorName(authorName).forEach(b -> printer.printLine(convertObjectStringView(b)));
    }

    @ShellMethod(value = "Find book by genre name", key = {"find-book-genre", "fbg"})
    public void findBookByGenreName(@ShellOption String genreName) {
        service.getByGenreName(genreName).forEach(b -> printer.printLine(convertObjectStringView(b)));
    }

    @ShellMethod(value = "Find all book", key = {"find-all-books", "fab"})
    public void findAllBooks() {
        List<Book> books = service.getAll();
        books.forEach(b -> printer.printLine(convertObjectStringView(b)));
    }

    @ShellMethod(value = "Add book to repository", key = {"add-book", "ab"})
    public String addBook(@ShellOption String name,
                          @ShellOption String author,
                          @ShellOption String genre) {
        service.add(name, author, genre);
        return "Успешно";
    }

    @ShellMethod(value = "Update book by id", key = {"update-book", "ub"})
    public String updateBook(@ShellOption long id,
                             @ShellOption String name,
                             @ShellOption String author,
                             @ShellOption String genre) {
        int rows = service.update(id, name, author, genre);
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book", "db"})
    public String deleteBook(@ShellOption long id) {
        int rows = service.deleteById(id);
        return String.format("Изменено (%s) строк(a)", rows);
    }

    private String convertObjectStringView(Book book) {
        return String.format(STRING_ROW_TEMPLATE,
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getGenre().getName());
    }
}
