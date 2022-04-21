package com.polozov.bookproject.dao;

import com.polozov.bookproject.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where a.name = :name")
    Author findByName(@Param("name") String authorName);
}
