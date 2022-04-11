package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.service.GenreService;
import com.polozov.bookproject.util.DataPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService service;
    private final DataPrinter printer;
    private static final String STRING_ROW_TEMPLATE = "%d - %s";

    @ShellMethod(value = "Find genre by id", key = {"find-genre-id", "fgi"})
    public void findGenreById(@ShellOption long id) {
        Optional<Genre> genreOptional = service.getById(id);
        genreOptional.ifPresent(genre -> printer.printLine(convertObjectStringView(genre)));
    }

    @ShellMethod(value = "Find genre by name", key = {"find-genre-name", "fgn"})
    public void findGenreByName(@ShellOption String name) {
        printer.printLine(convertObjectStringView(service.getByName(name)));
    }

    @ShellMethod(value = "Find all genres", key = {"find-all-genres", "fag"})
    public void findAllGenres() {
        service.getAll().forEach(g -> printer.printLine(convertObjectStringView(g)));
    }

    @ShellMethod(value = "Add genre to repository", key = {"add-genre", "ag"})
    public String addGenre(@ShellOption String name) {
        service.add(name);
        return "Успешно";
    }

    @ShellMethod(value = "Update genre by id", key = {"update-genre", "ug"})
    public String updateGenre(@ShellOption long id,
                               @ShellOption String name) {
        int rows = service.update(id, name);
        return String.format("Изменено %s строк", rows);
    }

    @ShellMethod(value = "Delete genre by id", key = {"delete-genre", "dg"})
    public String deleteAuthor(@ShellOption long id) {
        int rows = service.deleteById(id);
        return String.format("Изменено (%s) строк(a)", rows);
    }

    private String convertObjectStringView(Genre genre) {
        return String.format(STRING_ROW_TEMPLATE,
                genre.getId(),
                genre.getName());
    }
}
