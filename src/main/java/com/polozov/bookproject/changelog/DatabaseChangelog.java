package com.polozov.bookproject.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.polozov.bookproject.domain.Book;
import org.bson.Document;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", runAlways = true, author = "kwazart")
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenres", author = "kwazart")
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> genreCollections = db.getCollection("genres");
        List<Document> genreList = List.of(
                new Document().append("name", "Детектив").append("_id", "10"),
                new Document().append("name", "Мистика").append("_id", "20"),
                new Document().append("name", "Приключения").append("_id", "30"),
                new Document().append("name", "Фэнтези").append("_id", "40"),
                new Document().append("name", "Образовательное").append("_id", "50")
        );
        genreCollections.insertMany(genreList);
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "kwazart")
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> authorCollections = db.getCollection("authors");
        List<Document> authorList = List.of(
                new Document().append("name", "Агата Кристи").append("_id", "10"),
                new Document().append("name", "Роберт Мартин").append("_id", "20"),
                new Document().append("name", "Джон Толкин").append("_id", "30"),
                new Document().append("name", "Говард Лавкрафт").append("_id", "40")
        );
        authorCollections.insertMany(authorList);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "kwazart")
    public void insertBooks(MongoDatabase db) {
        MongoCollection<Document> bookCollections = db.getCollection("books");
        List<Document> bookList = List.of(
                new Document()
                        .append("name", "Пуаро")
                        .append("_id", "10")
                        .append("author", "Агата Кристи")
                        .append("genre", "Детектив"),
                new Document()
                        .append("name", "Чистый код")
                        .append("_id", "20")
                        .append("author", "Роберт Мартин")
                        .append("genre", "Образовательное"),
                new Document()
                        .append("name", "Чистая архитектура")
                        .append("_id", "30")
                        .append("author", "Роберт Мартин")
                        .append("genre", "Образовательное"),
                new Document()
                        .append("name", "Властелин колец")
                        .append("_id", "40")
                        .append("author", "Джон Толкин")
                        .append("genre", "Фэнтези"),
                new Document()
                        .append("name", "Сильмариллион")
                        .append("_id", "50")
                        .append("author", "Джон Толкин")
                        .append("genre", "Фэнтези"),
                new Document()
                        .append("name", "Хребты безумия")
                        .append("_id", "60")
                        .append("author", "Говард Лавкрафт")
                        .append("genre", "Мистика"),
                new Document()
                        .append("name", "Зов Ктулху")
                        .append("_id", "70")
                        .append("author", "Говард Лавкрафт")
                        .append("genre", "Мистика")
        );
        bookCollections.insertMany(bookList);
    }

    @ChangeSet(order = "005", id = "insertComments", author = "kwazart")
    public void insertComments(MongoDatabase db) {
        MongoCollection<Document> commentCollections = db.getCollection("comments");
        List<Document> commentList = List.of(
                new Document()
                        .append("text", "Отлично")
                        .append("book", new Document().append("_id", "10")),
                new Document()
                        .append("text", "Замечательно")
                        .append("book", new Document().append("_id", "10")),
                new Document()
                        .append("text", "Интересно")
                        .append("book",new Document().append("_id", "10")),
                new Document()
                        .append("text", "Супер")
                        .append("book", new Document().append("_id", "20")),
                new Document()
                        .append("text", "Восхитительно")
                        .append("book", new Document().append("_id", "20")),
                new Document()
                        .append("text", "Захватывающе")
                        .append("book", new Document().append("_id", "30")),
                new Document()
                        .append("text", "Закрученно")
                        .append("book", new Document().append("_id", "40"))

        );
        commentCollections.insertMany(commentList);
    }
}
