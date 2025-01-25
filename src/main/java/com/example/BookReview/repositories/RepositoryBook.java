package com.example.BookReview.repositories;

import com.example.BookReview.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Books
 */
@Repository
public interface RepositoryBook extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

    Optional<Book> findBookById(Long id);

    List<Book> findByAuthor(String author);
}
