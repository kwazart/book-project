package com.polozov.bookproject;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class BookProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookProjectApplication.class, args);
	}
}
