package com.polozov.bookproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final long authorId;
    private final long genreId;

    @Override
    public String toString() {
        return "id: " + id +
                " name: " + name + '\'' +
                " authorId: " + authorId +
                " genreId: " + genreId;
    }
}

