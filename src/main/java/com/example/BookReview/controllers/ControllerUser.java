package com.example.BookReview.controllers;

import com.example.BookReview.dto.UserDto;
import com.example.BookReview.services.ServiceUser;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for handling User Management
 */

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class ControllerUser {

    final ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    /**
     * getting all Users
     *
     * @return users
     */
    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return serviceUser.getAllUsers();
    }

    /**
     * getting User by id
     *
     * @param id input
     * @return user
     */
    @GetMapping("/{id}")
    public Optional<UserDto> getUser(@PathVariable Long id) {
        return serviceUser.getUserById(id);
    }


}
