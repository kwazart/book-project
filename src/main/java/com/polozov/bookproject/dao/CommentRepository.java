package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String book);
}
