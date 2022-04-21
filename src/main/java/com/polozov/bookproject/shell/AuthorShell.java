package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.service.AuthorService;
import com.polozov.bookproject.util.DataPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService service;
    private final DataPrinter printer;
    private static final String STRING_ROW_TEMPLATE = "%d - %s";

    @ShellMethod(value = "Find author by id", key = {"find-author-id", "fai"})
    public void findAuthorById(@ShellOption long id) {
        Optional<Author> authorOptional = service.getById(id);
        authorOptional.ifPresent(author -> printer.printLine(convertObjectStringView(author)));
    }

    @ShellMethod(value = "Find author by name", key = {"find-author-name", "fan"})
    public void findAuthorByName(@ShellOption String name) {
        printer.printLine(convertObjectStringView(service.getByName(name)));
    }

    @ShellMethod(value = "Find all authors", key = {"find-all-authors", "faa"})
    public void findAllAuthors() {
        service.getAll().forEach(a -> printer.printLine(convertObjectStringView(a)));
    }

    @ShellMethod(value = "Add author to repository", key = {"add-author", "aa"})
    public String addAuthor(@ShellOption String name) {
        service.add(name);
        return "Успешно";
    }

    @ShellMethod(value = "Update author by id", key = {"update-author", "ua"})
    public String updateAuthor(@ShellOption long id,
                             @ShellOption String name) {
        service.update(id, name);
        return "Успешно";
    }

    @ShellMethod(value = "Delete author by id", key = {"delete-author", "da"})
    public String deleteAuthor(@ShellOption long id) {
        service.deleteById(id);
        return "Успешно";
    }

    private String convertObjectStringView(Author author) {
        return String.format(STRING_ROW_TEMPLATE,
                author.getId(),
                author.getName());
    }
}
