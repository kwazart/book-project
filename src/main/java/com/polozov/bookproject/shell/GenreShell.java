package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.service.GenreService;
import com.polozov.bookproject.util.PrinterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService service;
    private final PrinterUtil printer;

    @ShellMethod(value = "Find genre by id", key = {"find-genre-id", "fgi"})
    public void findGenreById(@ShellOption long id) {
        printer.printObject(service.getById(id));
    }

    @ShellMethod(value = "Find genre by name", key = {"find-genre-name", "fgn"})
    public void findGenreByName(@ShellOption String name) {
        printer.printObject(service.getByName(name));
    }

    @ShellMethod(value = "Find all genres", key = {"find-all-genres", "fag"})
    public void findAllGenres() {
        printer.printObjectList(service.getAll());
    }

    @ShellMethod(value = "Add genre to repository", key = {"add-genre", "ag"})
    public String addGenre(@ShellOption String name) {
        int rows = service.add(new Genre(0, name));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Update genre by id", key = {"update-genre", "ug"})
    public String updateGenre(@ShellOption long id,
                               @ShellOption String name) {
        int rows = service.update(new Genre(id, name));
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Delete genre by id", key = {"delete-genre", "dg"})
    public String deleteAuthor(@ShellOption long id) {
        int rows = service.deleteById(id);
        return String.format("Изменено (%s) строк(a)", rows);
    }
}
