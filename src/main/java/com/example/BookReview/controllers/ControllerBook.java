package com.example.BookReview.controllers;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.services.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling Books Management
 */
@RestController
@RequestMapping("/books")
public class ControllerBook {

    @Autowired
    ServiceBook serviceBook;


    /**
     * @return all books
     */
    @GetMapping
    public Iterable<BookDto> get() {
        return serviceBook.getAllBooks();
    }

    /**
     * find book by id
     *
     * @param id input
     * @return Book
     */
    @GetMapping("/id")
    public Optional<BookDto> getById(@PathVariable Long id) {
        return serviceBook.getBookById(id);
    }


    /**
     * find books by author
     *
     * @param author name
     * @return bookslist by author
     */
    @GetMapping("/{author}")
    public List<BookDto> getByAuthor(@PathVariable String author) {
        return serviceBook.getBooksByAuthor(author);
    }

    /**
     * saving book
     *
     * @param bookDto current book
     * @return response of saved book
     */
    @PostMapping("/new")
    public ResponseEntity<BookDto> add(@RequestBody BookDto bookDto) {
        BookDto book = serviceBook.createBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    /**
     * deleting book
     *
     * @param id current book
     * @return response with deleted book
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<BookDto> isDeleted = serviceBook.deleteBook(id);
        if (isDeleted.isPresent()) {
            return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * updating book
     *
     * @param bookDto new Value
     * @param id      of changing book
     * @return new book
     */
    @PostMapping("/update")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto, @PathVariable Long id) {
        return new ResponseEntity<>(serviceBook.updateBook(bookDto, id), HttpStatus.CREATED);
    }

}
