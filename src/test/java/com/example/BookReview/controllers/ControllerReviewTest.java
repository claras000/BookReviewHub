package com.example.BookReview.controllers;

import com.example.BookReview.dto.ReviewDto;
import com.example.BookReview.services.ServiceReview;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ControllerReviewTest {

    private final Long ID = Long.valueOf(1);
    private final String TEXT = "I really loved the book. Momo is an inspiring little girl!";
    private final int GRADE = 4;
    private final String TITLE = "very good book!";
    private final Long USERID = Long.valueOf(1);
    private final Long BOOKID = Long.valueOf(2);

    private final Calendar calendar = Calendar.getInstance();
    private final Date DATE = new Date(calendar.getTimeInMillis());
    @Mock
    ServiceReview serviceReview;
    @InjectMocks
    private ControllerReview classUnderTest;

    @Test
    void getAllReviews_Test() {
        //Arrange
        ReviewDto reviewDto = new ReviewDto(ID, TEXT, GRADE, TITLE, BOOKID, USERID);
        Iterable<ReviewDto> reviewDtoList = Collections.singletonList(reviewDto);
        doReturn(reviewDtoList).when(serviceReview).getAllReviews();
        //Act
        Iterable<ReviewDto> result = classUnderTest.getAllReviews();
        //Assert
        Assertions.assertThat(result).isEqualTo(reviewDtoList);
    }

    @Test
    void addReview_Test() {
        //Arrange
        ReviewDto reviewDto = new ReviewDto(ID, TEXT, GRADE, TITLE, BOOKID, USERID);
        ResponseEntity<ReviewDto> response = new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
        doReturn(reviewDto).when(serviceReview).addReview(BOOKID, USERID, TITLE, TEXT, GRADE);
        //Act
        ResponseEntity<ReviewDto> result = classUnderTest.addReview(reviewDto);
        //Assert
        Assertions.assertThat(result).isEqualTo(response);
    }
}