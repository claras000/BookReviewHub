package com.example.BookReview.repositories;

import com.example.BookReview.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryBook extends CrudRepository<Book,Long> {


}
