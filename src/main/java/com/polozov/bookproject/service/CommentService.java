package com.polozov.bookproject.service;

import com.polozov.bookproject.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(long id);
    List<Comment> getByBookId(long bookId);
    Comment add(String text, long bookId);
    Comment update(long commentId, String text, long bookId);
    void deleteById(long id);
}
