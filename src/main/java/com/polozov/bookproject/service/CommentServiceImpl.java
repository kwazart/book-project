package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.BookRepository;
import com.polozov.bookproject.dao.CommentRepository;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookService bookService;

    @Override
    public Optional<Comment> getById(String id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Comment> getByBookId(String bookId) {
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Incorrect book id");
        }
        return findAllByBook(bookOptional.get());
    }

    @Transactional
    @Override
    public Comment add(String text, String bookId) {
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isEmpty()) {
            return null;
        }
        return repository.save(new Comment(text, bookOptional.get()));
    }

    @Transactional
    @Override
    public Comment update(String commentId, String text, String bookId) {
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Book not found");
        }
        return repository.save(new Comment(commentId, text, bookOptional.get()));
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByBookId(String bookId) {
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isPresent()) {
            List<Comment> allByBook = findAllByBook(bookOptional.get());
            for (Comment c : allByBook) {
                deleteById(c.getId());
            }
        }
    }

    // работает только в таком варианте
    // по дефолту в MongoRepository не ищет по findAllByBook (возвращает 0)
    private List<Comment> findAllByBook(Book book) {
        return repository.findAll().stream().filter(c -> c.getBook().getId().equals(book.getId())).collect(Collectors.toList());
    }
}
