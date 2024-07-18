package com.example.BookReview.controllers;

import com.example.BookReview.models.User;
import com.example.BookReview.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;

    @GetMapping
    public Iterable<User> getAllUsers(){
        return serviceUser.getAllUsers();
    }






    /**
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return serviceUser.getUserById(id);
    }
**/
    

}
