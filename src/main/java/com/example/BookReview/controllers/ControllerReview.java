package com.example.BookReview.controllers;

import com.example.BookReview.models.Review;
import com.example.BookReview.services.ServiceReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ControllerReview {

    @Autowired
    ServiceReview serviceReview;

    @GetMapping
    public Iterable<Review> getAllReviews(){
    return serviceReview.getAllReviews();
    }
}
