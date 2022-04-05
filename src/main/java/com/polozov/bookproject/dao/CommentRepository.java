package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);
    List<Comment> findByBook(Book book);
    List<Comment> findAll();
    Comment save(Comment comment);
    int update(long id, String text, Book book);
    int deleteById(long id);
}
