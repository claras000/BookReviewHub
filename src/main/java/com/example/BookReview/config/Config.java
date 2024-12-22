package com.example.BookReview.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * Config for creatings current date
 */
@Configuration
@EnableAspectJAutoProxy
public class Config {

    /**
     *
     * @return current date
     */
    @Bean("date")
    public Date getDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate localDate = currentDateTime.toLocalDate();
        java.sql.Date newDate = java.sql.Date.valueOf(localDate);
        return newDate;
    }




}
