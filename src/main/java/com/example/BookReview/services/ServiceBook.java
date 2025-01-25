package com.example.BookReview.services;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.models.Book;
import com.example.BookReview.repositories.RepositoryBook;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Book Service
 */
@Service
public class ServiceBook {

    @Autowired
    RepositoryBook repositoryBook;

    @Autowired
    private ApplicationContext context;

    /**
     * get all books
     *
     * @return booklist
     */
    public Iterable<BookDto> getAllBooks() {
        return StreamSupport.stream(repositoryBook.findAll().spliterator(), false).map(this::mapToDto).toList();
    }

    /**
     * get book by id
     *
     * @param id input
     * @return book
     */
    public Optional<BookDto> getBookById(Long id) {
        return repositoryBook.findBookById(id).map(this::mapToDto);
    }

    /**
     * returns booklist by author
     *
     * @param author input
     * @return booklist
     */
    public List<BookDto> getBooksByAuthor(String author) {
        return repositoryBook.findByAuthor(author).stream().map(this::mapToDto).toList();
    }

    /**
     * returns booklist by title
     *
     * @param title current title
     * @return
     */
    public List<BookDto> getBooksByName(String title) {
        return repositoryBook.findByName(title).stream().map(this::mapToDto).toList();
    }


    /**
     * create book
     *
     * @param bookDto book data
     * @return new book
     */
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        Book newBook = repositoryBook.save(mapToBook(bookDto, book));
        return mapToDto(newBook);
    }

    /**
     * updating book
     *
     * @param bookDto book data
     * @param id      of book
     * @return new book
     */
    public BookDto updateBook(BookDto bookDto, long id) {
        Book book = repositoryBook.findBookById(id).orElseThrow(() -> new EntityNotFoundException("no books found with id: " + id));
        Book updatedBook = repositoryBook.save(mapToBook(bookDto, book));
        return mapToDto(updatedBook);
    }

    /**
     * deleting book
     *
     * @param id of book to delete
     * @return deleted book
     */
    public Optional<BookDto> deleteBook(Long id) {
        Book book = repositoryBook.findById(id).orElseThrow(() -> new RuntimeException("Book could not be deleted"));
        repositoryBook.delete(book);
        return Optional.of(mapToDto(book));
    }


    /**
     * mapping book to Data Transfer Object Book
     *
     * @param book input
     * @return Book Transfer Object
     */
    private BookDto mapToDto(Book book) {
        BookDto bookDto = new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getPublicationdate());
        return bookDto;
    }

    /**
     * mapping Data Transfer Object book to book
     *
     * @param bookDto input
     * @return book
     */
    private Book mapToBook(BookDto bookDto, Book book) {
        book.setName(bookDto.name());
        book.setPublicationdate((Date) context.getBean("date"));
        book.setAuthor(bookDto.author());
        return book;
    }


}
