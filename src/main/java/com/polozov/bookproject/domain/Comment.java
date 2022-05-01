package com.polozov.bookproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String text;

    @Field(name = "book")
    private String bookId;

    public Comment(String text, String bookId) {
        this.text = text;
        this.bookId = bookId;
    }
}
