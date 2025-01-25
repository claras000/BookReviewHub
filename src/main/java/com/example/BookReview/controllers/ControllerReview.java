package com.example.BookReview.controllers;

import com.example.BookReview.dto.ReviewDto;
import com.example.BookReview.services.ServiceReview;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling Review Management
 */
@RestController
@RequestMapping("/reviews")
public class ControllerReview {

    final ServiceReview serviceReview;

    public ControllerReview(ServiceReview serviceReview) {
        this.serviceReview = serviceReview;
    }

    /**
     * getting all reviews from all books
     *
     * @return all reviews
     */
    @GetMapping
    public Iterable<ReviewDto> getAllReviews() {
        return serviceReview.getAllReviews();
    }

    /**
     * creating new reviews
     *
     * @param reviewDto review transferObject
     * @return new review
     */
    @PostMapping("/new")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto review = serviceReview.addReview(reviewDto.getBook_id(), reviewDto.getUser_id(), reviewDto.getTitle(), reviewDto.getReviewText(), reviewDto.getGrade());
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }


}
