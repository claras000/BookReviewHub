package com.example.BookReview.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;



@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    private String name;

    private String author;

    private Date publicationdate;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();


    public void setId(Long id) {
        this.book_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationdate(Date publicationdate) {
        this.publicationdate = publicationdate;
    }

    public Long getId() {
        return book_id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublicationdate() {
        return publicationdate;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }


    public List<Review> getReviews() {
        return reviews;
    }


    public void addReview(Review review){
        reviews.add(review);
        review.setBook(this);
    }

    //noch schreiben
    public void removeReview(Review review){

    }
}
