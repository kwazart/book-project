package com.polozov.bookproject.shell;

import com.polozov.bookproject.domain.Comment;
import com.polozov.bookproject.service.CommentService;
import com.polozov.bookproject.util.DataPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentService service;
    private final DataPrinter printer;
    private static final String STRING_ROW_TEMPLATE = "%d - %s - %s";

    @ShellMethod(value = "Find comment by id", key = {"find-comment-id", "fci"})
    public void findCommentById(@ShellOption long id) {
        Optional<Comment> optionalComment = service.getById(id);
        optionalComment.ifPresent(comment -> printer.printLine(convertObjectStringView(comment)));
    }

    @ShellMethod(value = "Find all comments by book", key = {"find-all-comment-book", "facb"})
    public void findAllCommentByBookId(@ShellOption long bookId) {
        service.getByBookId(bookId).forEach(c -> printer.printLine(convertObjectStringView(c)));
    }

    @ShellMethod(value = "Add comment to repository", key = {"add-comment", "ac"})
    public String addComment(@ShellOption long bookId,
                             @ShellOption String text) {
        service.add(text, bookId);
        return "Успешно";
    }

    @ShellMethod(value = "Update comment by id", key = {"update-comment", "uс"})
    public String updateComment(@ShellOption long id,
                                @ShellOption String text,
                                @ShellOption long bookId) {
        service.update(id, text, bookId);
        return "Успешно";
    }

    @ShellMethod(value = "Delete comment by id", key = {"delete-comment", "dс"})
    public String deleteComment(@ShellOption long id) {
        service.deleteById(id);
        return "Успешно";
    }


    private String convertObjectStringView(Comment comment) {
        return String.format(STRING_ROW_TEMPLATE,
                comment.getId(),
                comment.getBook().getName(),
                comment.getText());
    }
}
