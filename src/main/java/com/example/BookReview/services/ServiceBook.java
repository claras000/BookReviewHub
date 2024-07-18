package com.example.BookReview.services;

import com.example.BookReview.models.Book;
import com.example.BookReview.repositories.RepositoryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBook {

    @Autowired
    RepositoryBook repositoryBook;

    public Optional<Book> getBookById(Long id){
        return repositoryBook.findById(id);

    }

    public Iterable<Book> getAllBooks(){
        return repositoryBook.findAll();
    }

}
