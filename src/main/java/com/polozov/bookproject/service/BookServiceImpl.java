package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.BookRepository;
import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import com.polozov.bookproject.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Optional<Book> getById(String id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Book> getByName(String name) {
        return repository.findAllByName(name);
    }

    @Transactional
    @Override
    public List<Book> getByAuthorName(String authorName) {
        return repository.findAllByAuthor(authorName);
    }

    @Transactional
    @Override
    public List<Book> getByGenreName(String genreName) {
        return repository.findAllByGenre(genreName);
    }

    @Transactional
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

        return repository.save(new Book(bookName, author.getName(), genre.getName()));
    }

    @Transactional
    @Override
    public Book update(String id, String bookName, String authorName, String genreName) {
        Author author = authorService.getByName(authorName);
        Genre genre = genreService.getByName(genreName);
        if (author == null || genre == null) {
            throw new ObjectNotFoundException("Object not found");
        }
        return repository.save(new Book(id, bookName, author.getName(), genre.getName()));
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByAuthor(String authorName) {
        repository.deleteAllByAuthor(authorName);
    }

    @Transactional
    @Override
    public void deleteByGenre(String genreName) {
        repository.deleteAllByGenre(genreName);
    }

    @Transactional
    @Override
    public void updateAllAuthors(String oldAuthorName, String newAuthorName) {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            if (book.getAuthor().equals(oldAuthorName)) {
                book.setAuthor(newAuthorName);
                repository.save(book);
            }
        }
    }

    @Transactional
    @Override
    public void updateAllGenres(String oldGenreName, String newGenreName) {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            if (book.getAuthor().equals(oldGenreName)) {
                book.setAuthor(newGenreName);
                repository.save(book);
            }
        }
    }
}
