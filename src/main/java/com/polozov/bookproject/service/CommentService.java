package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(String id);
    List<Comment> getByBookId(String bookId);
    Comment add(String text, String bookId);
    Comment update(String commentId, String text, String bookId);
    void deleteById(String id);
    void deleteByBookId(String bookId);
}
