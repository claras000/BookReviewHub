package com.example.BookReview.services;

import com.example.BookReview.models.User;
import com.example.BookReview.repositories.RepositoryUser;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class ServiceUser{

    @Autowired
    RepositoryUser repositoryUser;

    public Optional<User> getUserById(Long id){
        return repositoryUser.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
       return repositoryUser.findByEmail(email);
    }

    public Iterable<User> getAllUsers(){
        return repositoryUser.findAll();
    }


    public void save(User user) {
        repositoryUser.save(user);
    }
}
