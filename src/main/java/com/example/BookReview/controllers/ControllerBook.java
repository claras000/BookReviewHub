package com.example.BookReview.controllers;

import com.example.BookReview.models.Book;
import com.example.BookReview.services.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ControllerBook {

    @Autowired
    ServiceBook serviceBook;


    @GetMapping
    public Iterable<Book> getAllBooks(){
        return serviceBook.getAllBooks();
    }

}
