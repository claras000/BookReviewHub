package com.example.BookReview.repositories;

import com.example.BookReview.models.Book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //nicht echte DB genutzt
class RepositoryBookTest {

    @Autowired
    private RepositoryBook classUnderTest;

    @Test
    public void testFindById() {
        //Arrange
        Book book = Book.builder().name("Test Book").build();
        classUnderTest.save(book);

        // Act
        Optional<Book> foundBook = classUnderTest.findById(book.getId());

        // Assert
        Assertions.assertThat(foundBook).isPresent();
        Assertions.assertThat(foundBook.get().getName()).isEqualTo("Test Book");
    }

    @Test
    public void testSaveBook(){
        //Arrange
        Book book = Book.builder().name("Test Book").build();

        //Act
        Book savedBook = classUnderTest.save(book);

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getBook_id()).isGreaterThan(0);
        Assertions.assertThat(savedBook.getName()).isEqualTo("Test Book");
    }

    @Test
    public void testFindAllBooks(){
        //Arrange
        Book book1 = Book.builder().name("Momo").build();
        Book book2 = Book.builder().name("die Pest").build();
        classUnderTest.save(book1);
        classUnderTest.save(book2);

        //Act
        List<Book> bookList = (List<Book>) classUnderTest.findAll();

        //Assert
        Assertions.assertThat(bookList).isNotNull();
        Assertions.assertThat(bookList.size()).isEqualTo(2);
    }

    @Test
    public void testFindByIdBook(){
        //Arrange
        Book book = Book.builder().name("Momo").build();
        Book savedBook = classUnderTest.save(book);

        //Act
        Optional<Book> bookById = classUnderTest.findById(savedBook.getId());

        //Assert
        Assertions.assertThat(bookById).isPresent();
    }

    /**
     * custom query method
     */
    @Test
    public void testFindBookListByAuthor(){
        //Arrange
        Book book1 = Book.builder()
                .name("Momo")
                .author("Ende").build();
        Book book2 = Book.builder()
                .name("Die unendliche Geschichte")
                .author("Ende").build();
        Book book3 = Book.builder()
                .name("Krabat")
                .author("Otfried Preußler").build();
        classUnderTest.save(book1);
        classUnderTest.save(book2);
        classUnderTest.save(book3);

        //Act
        List BookByAuthorList = classUnderTest.findByAuthor("Ende");

        Assertions.assertThat(BookByAuthorList).isNotNull();
        Assertions.assertThat(BookByAuthorList.size()).isEqualTo(2);
    }

    /*@Test
    public void UpdateBook(){
        //Arrange
        Book book = Book.builder()
                .name("Momo")
                .author("Ende").build();

        classUnderTest.save(book);

        Book savedBook = classUnderTest.findBookById(book.getBook_id());
        savedBook.setAuthor("a");
        savedBook.setName("b");

        Book updateBook = classUnderTest.save(savedBook);

        Assertions.assertThat(updateBook).isNotNull();
        Assertions.assertThat(updateBook.getAuthor()).isEqualTo("a");
        Assertions.assertThat(updateBook.getName()).isEqualTo("b");

    }*/

    @Test
    public void deleteBook(){
        //Arrange
        Book book = Book.builder()
                .name("Momo")
                .author("Ende")
                .build();

        classUnderTest.save(book);

        //Act
        classUnderTest.deleteById(book.getBook_id());
        Optional<Book> emptyBooksList = classUnderTest.findById(book.getId());

        //Assert
        Assertions.assertThat(emptyBooksList).isEmpty();
    }

}