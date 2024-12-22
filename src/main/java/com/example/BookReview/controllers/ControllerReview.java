package com.example.BookReview.controllers;

import com.example.BookReview.dto.ReviewDto;
import com.example.BookReview.models.Review;
import com.example.BookReview.services.ServiceReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ControllerReview {

    @Autowired
    ServiceReview serviceReview;

    @GetMapping
    public Iterable<Review> getAllReviews() {
        return serviceReview.getAllReviews();
    }

    @PostMapping("/new")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto review = serviceReview.addReview(reviewDto.getBook_id(), reviewDto.getUser_id(), reviewDto.getTitle(), reviewDto.getReviewText(), reviewDto.getGrade());
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

}
