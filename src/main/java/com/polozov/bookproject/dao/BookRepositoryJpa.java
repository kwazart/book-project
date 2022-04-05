package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> findById(long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findByBookName(String name) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthorName(Author author) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author = :author", Book.class);
        query.setParameter("author", author);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenreName(Genre genre) {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("authors-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public int update(Book book) {
        Query query = em.createQuery("update Book b set b.name = :name, b.author = :author, b.genre = :genre where b.id = :id");
        query.setParameter("name", book.getName());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        query.setParameter("id", book.getId());
        return query.executeUpdate();
    }

    @Override
    public int deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
