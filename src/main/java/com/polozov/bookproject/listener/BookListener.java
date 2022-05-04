package com.polozov.bookproject.listener;

import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookListener extends AbstractMongoEventListener<Book> {

    private final CommentService commentService;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        commentService.deleteByBookId(id);
    }
}
