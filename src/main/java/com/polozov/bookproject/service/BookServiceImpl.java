package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.BookDao;
import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookDao dao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book getById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<Book> getByName(String name) {
        return dao.findByBookName(name);
    }

    @Override
    public List<Book> getByAuthorName(String authorName) {
        return dao.findByAuthorName(authorName);
    }

    @Override
    public List<Book> getByGenreName(String genreName) {
        return dao.findByGenreName(genreName);
    }

    @Override
    public List<Book> getAll() {
        return dao.findAll();
    }

    @Override
    public int add(Book book) {
        String authorName = book.getAuthor().getName();
        String genreName = book.getGenre().getName();
        Author author = authorService.getByName(authorName);
        Genre genre = genreService.getByName(genreName);
        if (author == null || genre == null) {
            return 0;
        }

        return dao.insert(new Book(book.getId(), book.getName(), author, genre));
    }

    @Override
    public int update(Book book) {
        String authorName = book.getAuthor().getName();
        String genreName = book.getGenre().getName();
        Author author = authorService.getByName(authorName);
        Genre genre = genreService.getByName(genreName);
        if (author == null || genre == null) {
            return 0;
        }
        return dao.update(new Book(book.getId(), book.getName(), author, genre));
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }
}
