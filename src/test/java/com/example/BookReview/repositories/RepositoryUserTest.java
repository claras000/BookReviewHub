package com.example.BookReview.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nicht echte DB genutzt
class RepositoryUserTest {

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findUserById() {
    }
}