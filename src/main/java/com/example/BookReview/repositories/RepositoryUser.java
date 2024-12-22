package com.example.BookReview.repositories;

import com.example.BookReview.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findUserById(Long id);
}
