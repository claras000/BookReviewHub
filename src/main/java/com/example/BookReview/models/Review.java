package com.example.BookReview.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    private String reviewText;
    private Integer grade;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonBackReference
    private User user;


    //wichtig
    public Long getId() {
        return review_id;
    }
}
