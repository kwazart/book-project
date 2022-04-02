package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.service.AuthorService;
import com.polozov.bookproject.util.PrinterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService service;
    private final PrinterUtil printer;

    @ShellMethod(value = "Find author by id", key = {"find-author-id", "fai"})
    public void findAuthorById(@ShellOption long id) {
        printer.printObject(service.getById(id));
    }

    @ShellMethod(value = "Find author by name", key = {"find-author-name", "fan"})
    public void findAuthorByName(@ShellOption String name) {
        printer.printObject(service.getByName(name));
    }

    @ShellMethod(value = "Find all authors", key = {"find-all-authors", "faa"})
    public void findAllAuthors() {
        printer.printObjectList(service.getAll());
    }

    @ShellMethod(value = "Add author to repository", key = {"add-author", "aa"})
    public String addAuthor(@ShellOption String name) {
        int rows = service.add(new Author(0, name));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Update author by id", key = {"update-author", "ua"})
    public String updateAuthor(@ShellOption long id,
                             @ShellOption String name) {
        int rows = service.update(new Author(id, name));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Delete author by id", key = {"delete-author", "da"})
    public String deleteAuthor(@ShellOption long id) {
        int rows = service.deleteById(id);
        return String.format("Изменено (%s) строк(a)", rows);
    }
}
