package com.example.BookReview.services;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.models.Book;
import com.example.BookReview.repositories.RepositoryBook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceBookTest {

    private static final String NAME = "Momo";
    private static final String AUTHOR = "Ende";
    private Date bookDate;
    private BookDto bookDto;
    private Book book;

    @InjectMocks
    private ServiceBook classUnderTest;
    @Mock
    private RepositoryBook repositoryBook;
    @Mock
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(21960, Calendar.DECEMBER, 32);
        bookDate = new Date(calendar.getTimeInMillis());

        bookDto = new BookDto(1L, NAME, AUTHOR, bookDate);
        book = Book.builder().book_id(1L).name(NAME).author(AUTHOR).publicationdate(bookDate).build();
    }

    @Test
    void getAllBooks_Test() {
        //Arrange
        List<BookDto> bookDtoList = Collections.singletonList(bookDto);
        List<Book> bookList = Collections.singletonList(book);
        Iterable<Book> bookIterable = bookList;
        doReturn(bookIterable).when(repositoryBook).findAll();

        //Act
        Iterable<BookDto> result = classUnderTest.getAllBooks();

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(bookDtoList);
    }

    @Test
    void getBookById_Test() {
        //Arrange
        doReturn(Optional.ofNullable(book)).when(repositoryBook).findBookById(1L);

        //Act
        Optional<BookDto> result = classUnderTest.getBookById(1L);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(Optional.ofNullable(bookDto));
    }

    @Test
    void getBooksByAuthor_Test() {
        //Arrange
        doReturn(Collections.singletonList(book)).when(repositoryBook).findByAuthor(AUTHOR);

        //Act
        List<BookDto> result = classUnderTest.getBooksByAuthor(AUTHOR);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(Collections.singletonList(bookDto));
    }

    @Test
    public void createBook_Test() {
        //Arrange
        doReturn(bookDate).when(context).getBean("date");
        doReturn(book).when(repositoryBook).save(Mockito.any(Book.class));

        //Act
        BookDto result = classUnderTest.createBook(bookDto);

        //Assert
        Assertions.assertThat(result.name()).isNotNull();
        Assertions.assertThat(result.id()).isEqualTo(1L);
    }


    @Test
    void updateBook_Test() {
        //Arrange
        BookDto newBookDto = new BookDto(1L, NAME, AUTHOR, bookDate);
        Book newBook = Book.builder().book_id(1L).name(NAME).author(AUTHOR).publicationdate(bookDate).build();
        doReturn(bookDate).when(context).getBean("date");

        doReturn(Optional.ofNullable(book)).when(repositoryBook).findBookById(1L);
        doReturn(newBook).when(repositoryBook).save(Mockito.any(Book.class));
        //Act
        BookDto result = classUnderTest.updateBook(bookDto, 1L);

        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(newBookDto);
    }

    @Test
    void deleteBook_Test() {
        //Arrange
        doReturn(Optional.ofNullable(book)).when(repositoryBook).findById(1L);

        //Act
        Optional<BookDto> result = classUnderTest.deleteBook(1L);
        //Assert
        Assertions.assertThat(result).isNotNull();
        verify(repositoryBook).delete(book);
        Assertions.assertThat(result).isEqualTo(Optional.ofNullable(bookDto));
    }
}
