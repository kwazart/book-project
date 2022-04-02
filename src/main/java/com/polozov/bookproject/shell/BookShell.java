package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.service.BookService;
import com.polozov.bookproject.util.PrinterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
public class BookShell {

    private final BookService service;
    private final PrinterUtil printer;

    @ShellMethod(value = "Find book by id", key = {"find-book-id", "fbi"})
    public void findBookById(@ShellOption long id) {
        printer.printObject(service.getById(id));
    }

    @ShellMethod(value = "Find book by name", key = {"find-book-name", "fbn"})
    public void findBookByName(@ShellOption String name) {
        printer.printObject(service.getByName(name));
    }

    @ShellMethod(value = "Find book by author name", key = {"find-book-author", "fba"})
    public void findBookByAuthorName(@ShellOption String authorName) {
        printer.printObjectList(service.getByAuthorName(authorName));
    }

    @ShellMethod(value = "Find book by genre name", key = {"find-book-genre", "fbg"})
    public void findBookByGenreName(@ShellOption String genreName) {
        printer.printObjectList(service.getByGenreName(genreName));
    }

    @ShellMethod(value = "Find all book", key = {"find-all-books", "fab"})
    public void findAllBooks() {
        printer.printObjectList(service.getAll());
    }

    @ShellMethod(value = "Add book to repository", key = {"add-book", "ab"})
    public String addBook(@ShellOption String name,
                          @ShellOption long authorId,
                          @ShellOption long genreId) {
        int rows = service.add(new Book(0, name, authorId, genreId));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Update book by id", key = {"update-book", "ub"})
    public String updateBook(@ShellOption long id,
                             @ShellOption String name,
                             @ShellOption long authorId,
                             @ShellOption long genreId) {
        int rows = service.update(new Book(id, name, authorId, genreId));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book", "db"})
    public String deleteBook(@ShellOption long id) {
        int rows = service.deleteById(id);
        return String.format("Изменено (%s) строк(a)", rows);
    }
}
