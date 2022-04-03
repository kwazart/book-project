package com.polozov.bookproject.util;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterUtil {

    private static final String LINE_SHORT_TEMPLATE = "%d - %s\n";
    private static final String LINE_LONG_TEMPLATE = "%d - %s - %s - %s\n";

    public void printAuthor(Author author) {
        System.out.printf(LINE_SHORT_TEMPLATE, author.getId(), author.getName());
    }

    public void printGenre(Genre genre) {
        System.out.printf(LINE_SHORT_TEMPLATE, genre.getId(), genre.getName());
    }

    public void printBook(Book book) {
        System.out.printf(LINE_LONG_TEMPLATE,
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getGenre().getName());
    }

    public void printAuthorList(List<Author> authors) {
        for (Author a : authors) {
            printAuthor(a);
        }
    }

    public void printGenreList(List<Genre> genres) {
        for (Genre g : genres) {
            printGenre(g);
        }
    }

    public void printBookList(List<Book> books) {
        for (Book b : books) {
            printBook(b);
        }
    }
}
