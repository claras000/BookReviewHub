package com.example.BookReview.repositories;

import com.example.BookReview.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Reviews
 */
@Repository
public interface
RepositoryReview extends CrudRepository<Review, Long> {

    Review findReviewById(Long id);

}
