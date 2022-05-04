package com.polozov.bookproject.listener;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.service.AuthorService;
import com.polozov.bookproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorListener extends AbstractMongoEventListener<Author> {

    private final BookService bookService;
    private final AuthorService authorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Author> event) {
        super.onBeforeConvert(event);
        System.out.println("BEFORE CONVERT");
        Author sourceAuthor = event.getSource();

        Optional<Author> oldAuthor = authorService.getById(sourceAuthor.getId());
        if (oldAuthor.isPresent()) {
            String newName = sourceAuthor.getName();
            String oldName = oldAuthor.get().getName();
            bookService.updateAllAuthors(oldName, newName);
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        Optional<Author> authorOptional = authorService.getById(id);
        authorOptional.ifPresent(author -> bookService.deleteByAuthor(author.getName()));
    }
}
