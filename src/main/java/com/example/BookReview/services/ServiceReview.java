package com.example.BookReview.services;

import com.example.BookReview.models.Review;
import com.example.BookReview.repositories.RepositoryReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceReview {

    @Autowired
    RepositoryReview repositoryReview;

    public Iterable<Review> getAllReviews(){
        return repositoryReview.findAll();
    }
}
