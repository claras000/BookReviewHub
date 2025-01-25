package com.example.BookReview.controllers;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.services.ServiceBook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ControllerBookTest {

    private static final String NAME = "Momo";
    private static final String AUTHOR = "Ende";
    private BookDto bookDto;
    private Date bookDate;

    @InjectMocks
    private ControllerBook classUnderTest;

    @Mock
    private ServiceBook serviceBook;

    @Mock
    private Optional<BookDto> optionalBookDto;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(21960, Calendar.DECEMBER, 32);
        bookDate = new Date(calendar.getTimeInMillis());
        bookDto = new BookDto(1L, NAME, AUTHOR, bookDate);
    }

    @Test
    void getAllBooks_Test() {
        //Arrange
        Iterable<BookDto> bookDtoList = Collections.singletonList(bookDto);
        doReturn(bookDtoList).when(serviceBook).getAllBooks();
        //Act
        Iterable<BookDto> result = classUnderTest.getAllBooks();
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(bookDtoList);
    }

    @Test
    void getBooksById_Test() {
        //Arrange
        Optional<BookDto> optionalBookDto = Optional.ofNullable(bookDto);
        doReturn(optionalBookDto).when(serviceBook).getBookById(1L);
        //Act
        Optional<BookDto> result = classUnderTest.getBooksById(1L);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(optionalBookDto);
    }

    @Test
    void getBooksByAuthor_Test() {
        //Arrange
        Iterable<BookDto> bookDtoList = Collections.singletonList(bookDto);
        doReturn(bookDtoList).when(serviceBook).getBooksByAuthor(AUTHOR);
        //Act
        List<BookDto> result = classUnderTest.getBooksByAuthor(AUTHOR);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(bookDtoList);
    }

    @Test
    void getBooksByName_Test() {
        //Arrange
        Iterable<BookDto> bookDtoList = Collections.singletonList(bookDto);
        doReturn(bookDtoList).when(serviceBook).getBooksByName(NAME);
        //Act
        List<BookDto> result = classUnderTest.getBooksByName(NAME);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(bookDtoList);
    }

    @Test
    void addBook_Test() {
        //Arrange
        ResponseEntity<BookDto> response = new ResponseEntity<>(bookDto, HttpStatus.CREATED);
        doReturn(bookDto).when(serviceBook).createBook(bookDto);
        //Act
        ResponseEntity<BookDto> result = classUnderTest.addBook(bookDto);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(response);
    }

    @Test
    void deleteBook_Test_exist() {
        //Arrange
        ResponseEntity<String> response = new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
        doReturn(optionalBookDto).when(serviceBook).deleteBook(1L);
        doReturn(true).when(optionalBookDto).isPresent();
        //Act
        ResponseEntity<String> result = classUnderTest.deleteBook(1L);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(response);
    }

    @Test
    void deleteBook_Test_notExist() {
        //Arrange
        ResponseEntity<String> response = new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        doReturn(optionalBookDto).when(serviceBook).deleteBook(1L);
        doReturn(false).when(optionalBookDto).isPresent();
        //Act
        ResponseEntity<String> result = classUnderTest.deleteBook(1L);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(response);
    }

    @Test
    void updateBook_Test() {
        //Arrange
        ResponseEntity<BookDto> response = new ResponseEntity<>(bookDto, HttpStatus.CREATED);
        doReturn(bookDto).when(serviceBook).updateBook(bookDto, 1L);
        //Act
        ResponseEntity<BookDto> result = classUnderTest.updateBook(bookDto, 1L);
        //Assert
        Assertions.assertThat(result).isEqualTo(response);
    }

}
