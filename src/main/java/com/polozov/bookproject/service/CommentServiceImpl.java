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

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookRepository bookRepository;

    @Override
    public Optional<Comment> getById(String id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<Comment> getByBookId(String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Incorrect book id");
        }
        return repository.findAllByBookId(bookOptional.get().getId());
    }

    @Transactional
    @Override
    public Comment add(String text, String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            return null;
        }
        return repository.save(new Comment(text, bookId));
    }

    @Transactional
    @Override
    public Comment update(String commentId, String text, String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Book not found");
        }
        return repository.save(new Comment(commentId, text, bookId));
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
