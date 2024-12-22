package com.example.BookReview.dto;

import java.sql.Date;

public record BookDto(Long id, String name, String author, Date publicationDate) {
}
