package com.polozov.bookproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {
    private final long id;
    private final String name;

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }
}
