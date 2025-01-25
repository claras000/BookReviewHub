package com.example.BookReview.controllers;

import com.example.BookReview.dto.UserDto;
import com.example.BookReview.services.ServiceUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ControllerUserTest {

    private final Long ID = Long.valueOf(1);
    private final String EMAIL = "anna22@uni-bremen.de";
    private final String USERNAME = "Anna1234";
    private final String PASSWORD = "you_password";
    private final String ROLE = "USER";

    private UserDto userDto;

    @InjectMocks
    private ControllerUser classUnderTest;

    @Mock
    private ServiceUser serviceUser;

    //new User

    @BeforeEach
    void setUp() {
        userDto = new UserDto(ID, EMAIL, USERNAME, PASSWORD, ROLE);
    }

    @Test
    void getAllUsers_Test() {
        //Arrange
        Iterable<UserDto> userDtoList = Collections.singletonList(userDto);
        doReturn(userDtoList).when(serviceUser).getAllUsers();
        //Act
        Iterable<UserDto> result = classUnderTest.getAllUsers();
        //Assert
        Assertions.assertThat(result).isEqualTo(userDtoList);
    }

    @Test
    void getUser_Test() {
        //Arrange
        Optional<UserDto> optionalUserDto = Optional.ofNullable(userDto);
        doReturn(optionalUserDto).when(serviceUser).getUserById(1L);
        //Act
        Optional<UserDto> result = classUnderTest.getUser(1L);
        //Assert
        Assertions.assertThat(result).isEqualTo(optionalUserDto);
    }
}