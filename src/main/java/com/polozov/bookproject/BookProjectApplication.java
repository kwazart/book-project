package com.polozov.bookproject;

import com.polozov.bookproject.dao.AuthorDao;
import com.polozov.bookproject.dao.BookDao;
import com.polozov.bookproject.dao.GenreDao;
import com.polozov.bookproject.domain.Author;
import com.polozov.bookproject.domain.Book;
import com.polozov.bookproject.domain.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BookProjectApplication.class, args);

		GenreDao genreDao = context.getBean(GenreDao.class);
		AuthorDao authorDao = context.getBean(AuthorDao.class);
		BookDao bookDao = context.getBean(BookDao.class);

		System.out.println("---------- GENRE ----------");

		System.out.println(genreDao.findAll());
		genreDao.insert(new Genre(2, "Phantasy"));
		genreDao.insert(new Genre(3, "Mystic"));
		System.out.println(genreDao.findAll());

		System.out.println("---------- AUTHOR ----------");

		System.out.println(authorDao.findAll());
		authorDao.insert(new Author(2, "Терри Пратчет"));
		authorDao.insert(new Author(3, "Говард Лавкрафт"));
		System.out.println(authorDao.findAll());

		System.out.println("---------- BOOK ----------");
		System.out.println(bookDao.findAll());
		bookDao.insert(new Book(2, "Хребты безумия", 3, 3));
		bookDao.insert(new Book(3, "Зов Ктулху", 3, 3));
		System.out.println(bookDao.findAll());
	}

}
