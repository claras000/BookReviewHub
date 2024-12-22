package com.example.BookReview.repositories;

import com.example.BookReview.models.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryReviewTest {

    private final RepositoryReview repositoryReview;

    @Autowired
    public RepositoryReviewTest(RepositoryReview repositoryReview) {
        this.repositoryReview = repositoryReview;
    }

    @Test
    public void ReviewRepository_SaveAll_ReturnsSaveReview(){
        Review review = Review.builder()
                .reviewText("awesome")
                .grade(1)
                .build();

        Review savedReview = repositoryReview.save(review);

        Assertions.assertThat(savedReview).isNotNull();
        Assertions.assertThat(savedReview.getReview_id()).isGreaterThan(0);
    }

    @Test
    public void ReviewRepository_FindById_ReturnsReview(){
        Review review = Review.builder()
                .reviewText("awesome")
                .grade(1)
                .build();

        repositoryReview.save(review);

       Review findByIdReview = repositoryReview.findReviewById(review.getId());

       Assertions.assertThat(findByIdReview).isNotNull();


    }

    @Test
    void ReviewRepository_GetAll_ReturnsAllReviews(){
        Review review1 = Review.builder()
                .reviewText("awesome")
                .grade(1)
                .build();

        Review review2 = Review.builder()
                .reviewText("oh no")
                .grade(4)
                .build();

        repositoryReview.save(review1);
        repositoryReview.save(review2);

        List<Review> getallReviews = (List<Review>) repositoryReview.findAll();

        Assertions.assertThat(getallReviews).isNotNull();
        Assertions.assertThat(getallReviews.size()).isEqualTo(2);

    }

    @Test
    void ReviewRepository_UpdateReview_ReturnReview() {
        Review review = Review.builder()
                .reviewText("awesome")
                .title("review")
                .grade(1)
                .build();

        repositoryReview.save(review);

        Review savingReview = repositoryReview.findReviewById(review.getId());
        savingReview.setReviewText("new");
        savingReview.setGrade(4);
        savingReview.setTitle("updated");

        Review updateReview = repositoryReview.save(savingReview);

        Assertions.assertThat(updateReview).isNotNull();
        Assertions.assertThat(updateReview.getId()).isEqualTo(review.getId());
        Assertions.assertThat(updateReview.getReviewText()).isEqualTo("new");
        Assertions.assertThat(updateReview.getGrade()).isEqualTo(4);
        Assertions.assertThat(updateReview.getTitle()).isEqualTo("updated");

    }

    @Test
    void ReviewRepository_DeleteReview_ReturnReviewIsEmpty() {
        Review review = Review.builder()
                .reviewText("awesome")
                .title("review")
                .grade(1)
                .build();

        repositoryReview.save(review);

        repositoryReview.deleteById(review.getId());
        Optional<Review> deletedReview = repositoryReview.findById(review.getId());

        Assertions.assertThat(deletedReview).isEmpty();
    }


}



