package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> findById(long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.id = :id", Comment.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Comment> findByBook(Book book) {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book = :book", Comment.class);
        query.setParameter("book", book);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Transactional
    @Override
    public int update(long id, String text, Book book) {
        Query query = em.createQuery("update Comment c set c.text = :text, c.book = :book where c.id = :id");
        query.setParameter("text", text);
        query.setParameter("book", book);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Transactional
    @Override
    public int deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
