package com.example.BookReview.controllers;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.services.ServiceBook;
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

    final
    ServiceBook serviceBook;

    public ControllerBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }


    /**
     * @return all books
     */
    @GetMapping
    public Iterable<BookDto> getAllBooks() {
        return serviceBook.getAllBooks();
    }

    /**
     * find book by id
     *
     * @param id input
     * @return Book
     */
    @GetMapping("/id/{id}")
    public Optional<BookDto> getBooksById(@PathVariable Long id) {
        return serviceBook.getBookById(id);
    }

    /**
     * get book by title
     *
     * @param title Book title
     * @return bookList
     */
    @GetMapping("/name/{title}")
    public List<BookDto> getBooksByName(@PathVariable String title) {
        return serviceBook.getBooksByName(title);
    }


    /**
     * find books by author
     *
     * @param author name
     * @return booksList by author
     */
    @GetMapping("/author/{author}")
    public List<BookDto> getBooksByAuthor(@PathVariable String author) {
        return serviceBook.getBooksByAuthor(author);
    }

    /**
     * saving book
     *
     * @param bookDto current book
     * @return response of saved book
     */
    @PostMapping("/new")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
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
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
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
     * @param bookDto dataTransferObject book
     * @param id      current id
     * @return changed book
     */
    @PostMapping("/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Long id) {
        return new ResponseEntity<>(serviceBook.updateBook(bookDto, id), HttpStatus.CREATED);
    }

}
