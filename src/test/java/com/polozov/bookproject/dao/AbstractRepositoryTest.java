package com.polozov.bookproject.dao;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"com.polozov.bookproject.repositories"})
public abstract class AbstractRepositoryTest {
}
