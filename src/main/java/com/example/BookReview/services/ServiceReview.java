package com.example.BookReview.services;

import com.example.BookReview.dto.ReviewDto;
import com.example.BookReview.models.Book;
import com.example.BookReview.models.Review;
import com.example.BookReview.models.User;
import com.example.BookReview.repositories.RepositoryBook;
import com.example.BookReview.repositories.RepositoryReview;
import com.example.BookReview.repositories.RepositoryUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service for the reviews of the books
 */
@Service
public class ServiceReview {

    @Autowired
    RepositoryReview repositoryReview;

    @Autowired
    RepositoryBook repositoryBook;

    @Autowired
    RepositoryUser repositoryUser;

    /**
     * Service to get all reviews
     *
     * @return all reviews
     */
    public Iterable<Review> getAllReviews() {
        return repositoryReview.findAll();
    }


    @Transactional
    public ReviewDto addReview(Long book_id, Long user_id, String title, String reviewText, Integer grade) {
        Book book = repositoryBook.findBookById(book_id).orElseThrow(() -> new EntityNotFoundException("no books found with id: " + book_id));

        User user = repositoryUser.findUserById(user_id);
        if (user == null) {
            throw new EntityNotFoundException("no user found with id: " + user_id);
        }

        Review review = new Review();
        book.addReview(review);
        review.setTitle(title);
        review.setReviewText(reviewText);
        review.setGrade(grade);
        review.setBook(book);
        review.setUser(user);

        return mapToDto(repositoryReview.save(review));
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReview_id(review.getId());
        reviewDto.setReviewText(review.getReviewText());
        reviewDto.setGrade(review.getGrade());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setBook_id(review.getBook().getBook_id());
        reviewDto.setUser_id(review.getUser().getUser_id());
        return reviewDto;
    }


}
