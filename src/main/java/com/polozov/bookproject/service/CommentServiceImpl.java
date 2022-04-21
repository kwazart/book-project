package com.polozov.bookproject.service;

import com.polozov.bookproject.dao.BookRepository;
import com.polozov.bookproject.dao.CommentRepository;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookRepository bookRepository;

    @Override
    public Optional<Comment> getById(long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Comment> getByBookId(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        return bookOptional.map(repository::findByBook).orElse(new ArrayList<>());
    }

    @Transactional
    @Override
    public Comment add(String text, long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            return null;
        }
        return repository.save(new Comment(0, text, bookOptional.get()));
    }

    @Transactional
    @Override
    public Comment update(long commentId, String text, long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Book not found");
        }
        return repository.save(new Comment(commentId, text, bookOptional.get()));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
