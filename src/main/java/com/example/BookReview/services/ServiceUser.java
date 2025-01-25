package com.example.BookReview.services;

import com.example.BookReview.dto.UserDto;
import com.example.BookReview.models.User;
import com.example.BookReview.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Review Service
 */
@Service
public class ServiceUser {

    @Autowired
    RepositoryUser repositoryUser;

    /**
     * getting all users
     *
     * @return users
     */
    public Iterable<UserDto> getAllUsers() {
        return StreamSupport.stream(repositoryUser.findAll().spliterator(), false).map(this::mapToDto).toList();
    }

    /**
     * getting User by id
     *
     * @param id current id
     * @return current users
     */
    public Optional<UserDto> getUserById(Long id) {
        return repositoryUser.findUserById(id).map(this::mapToDto);
    }

    /**
     * transform inout to DataTransferObject
     *
     * @param user inputs
     * @return UserDto
     */
    private UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
