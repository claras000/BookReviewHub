package com.example.BookReview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long review_id;

    private String reviewText;
    private Integer grade;
    private String title;

    private Long book_id;
    private Long user_id;
}
