package com.example.BookReview.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;



@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    private String name;

    private String author;

    private Date publicationdate;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //cascade type wird automatisch mitgespecihert
    @JsonIgnoreProperties("book")
    private List<Review> reviewList = new ArrayList<>();


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
        return reviewList;
    }


    public void addReview(Review review){
        reviewList.add(review);
    }

    //noch schreiben
    public void removeReview(Review review){

    }
}
