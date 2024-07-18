package com.example.BookReview;

import com.example.BookReview.models.Book;
import com.example.BookReview.models.Review;
import com.example.BookReview.models.User;
import com.example.BookReview.repositories.RepositoryBook;
import com.example.BookReview.repositories.RepositoryReview;
import com.example.BookReview.repositories.RepositoryUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.*;


@SpringBootApplication
@EntityScan(basePackages = "com.example.BookReview.models")
@EnableJpaRepositories(basePackages = "com.example.BookReview.repositories")
public class BookReviewApplication {



	public static void main(String[] args) {


		ConfigurableApplicationContext applicationContext  = SpringApplication.run(BookReviewApplication.class, args);


		//calendar
		Calendar calendar = Calendar.getInstance();

		//new book
		Book krabat= new Book();
		krabat.setAuthor("Otfried Preußler");
		krabat.setName("Krabat");
		calendar.set(2024, Calendar.JULY, 15);
		Date krabatDate = new Date(calendar.getTimeInMillis());
		krabat.setPublicationdate(krabatDate);

		//new book
		Book momo= new Book();
		momo.setAuthor("Michael Ende");
		momo.setName("Momo");
		calendar.set(21960, Calendar.DECEMBER, 32);
		Date momoDate = new Date(calendar.getTimeInMillis());
		momo.setPublicationdate(momoDate);

		//password encoder
		PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);

		//new User
		User anna = new User();
		anna.setEmail("anna22@uni-bremen.de");
		anna.setUsername("Anna1234");
		anna.setPassword(passwordEncoder.encode("you_password"));
		anna.setRole("USER");

		//new review

		Review annasReview01 = new Review();
		annasReview01.setReviewText("I really loved the book. Momo is an inspiring little girl!");
		annasReview01.setGrade(3);
		annasReview01.setUser(anna);

		momo.addReview(annasReview01);


		//saving
		RepositoryBook repositoryBook = applicationContext.getBean(RepositoryBook.class);
		repositoryBook.save(krabat);
		repositoryBook.save(momo);

		RepositoryReview repositoryReview = applicationContext.getBean(RepositoryReview.class);
		repositoryReview.save(annasReview01);

		RepositoryUser repositoryUser = applicationContext.getBean(RepositoryUser.class);
		repositoryUser.save(anna);




	}




}
