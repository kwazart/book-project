package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.BookRepository;
import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository repository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Optional<Book> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getByName(String name) {
        return repository.findByBookName(name);
    }

    @Override
    public List<Book> getByAuthorName(String authorName) {
        Author author = authorService.getByName(authorName);
        if (author == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return repository.findByAuthorName(author);
    }

    @Override
    public List<Book> getByGenreName(String genreName) {
        Genre genre = genreService.getByName(genreName);
        if (genre == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return repository.findByGenreName(genre);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Book add(String bookName, String authorName, String genreName) {
        Author author = authorService.getByName(authorName);
        Genre genre = genreService.getByName(genreName);
        if (author == null || genre == null) {
            throw new ObjectNotFoundException("Object not found");
        }

        return repository.save(new Book(0, bookName, author, genre));
    }

    @Transactional
    @Override
    public int update(long id, String bookName, String authorName, String genreName) {
        Author author = authorService.getByName(authorName);
        Genre genre = genreService.getByName(genreName);
        if (author == null || genre == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return repository.update(new Book(id, bookName, author, genre));
    }

    @Transactional
    @Override
    public int deleteById(long id) {
        return repository.deleteById(id);
    }
}
