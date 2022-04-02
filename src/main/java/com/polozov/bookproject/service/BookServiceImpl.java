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
    public Book getByName(String name) {
        return dao.findByBookName(name);
    }

    @Override
    public List<Book> getByAuthorName(String name) {
        Author author = authorService.getByName(name);
        return dao.findByAuthorId(author.getId());
    }

    @Override
    public List<Book> getByGenreName(String name) {
        Genre genre = genreService.getByName(name);
        return dao.findByGenreId(genre.getId());
    }

    @Override
    public List<Book> getAll() {
        return dao.findAll();
    }

    @Override
    public int add(Book book) {
        return dao.insert(book);
    }

    @Override
    public int update(Book book) {
        return dao.update(book);
    }

    @Override
    public int deleteById(long id) {
        return dao.deleteById(id);
    }
}
