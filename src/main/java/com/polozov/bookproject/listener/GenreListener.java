package com.polozov.bookproject.listener;

import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.service.BookService;
import com.polozov.bookproject.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreListener extends AbstractMongoEventListener<Genre> {

    private final BookService bookService;
    private final GenreService genreService;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        Optional<Genre> genreOptional = genreService.getById(id);
        genreOptional.ifPresent(genre -> bookService.deleteByGenre(genre.getName()));
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Genre> event) {
        super.onBeforeConvert(event);
        System.out.println("BEFORE CONVERT");
        Genre sourceGenre = event.getSource();

        Optional<Genre> oldGenre = genreService.getById(sourceGenre.getId());
        if (oldGenre.isPresent()) {
            String newName = sourceGenre.getName();
            String oldName = oldGenre.get().getName();
            bookService.updateAllGenres(oldName, newName);
        }
    }
}
