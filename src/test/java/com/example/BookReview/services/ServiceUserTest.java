package com.example.BookReview.services;

import com.example.BookReview.dto.BookDto;
import com.example.BookReview.dto.UserDto;
import com.example.BookReview.models.Book;
import com.example.BookReview.models.User;
import com.example.BookReview.repositories.RepositoryUser;
import lombok.Builder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ServiceUserTest {

    private final Long ID = Long.valueOf(1);
    private final String EMAIL = "anna22@uni-bremen.de";
    private final String USERNAME = "Anna1234";
    private final String PASSWORD = "you_password";
    private final String ROLE = "USER";

    private User user;
    private UserDto userDto;

    @InjectMocks
    private ServiceUser classUnderTest;

    @Mock
    private RepositoryUser repositoryUser;

    @Mock
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(ID, EMAIL, USERNAME, PASSWORD, ROLE);
        user = User.builder().id(ID).email(EMAIL).username(USERNAME).password(PASSWORD).role(ROLE).build();
    }

    @Test
    void getAllUsers_Test() {
        //Arrange
        List<UserDto> userDtoList = Collections.singletonList(userDto);
        List<User> userList = Collections.singletonList(user);
        Iterable<User> userIterable = userList;
        doReturn(userIterable).when(repositoryUser).findAll();

        //Act
        Iterable<UserDto> result = classUnderTest.getAllUsers();

        //Assert
        Assertions.assertThat(result).isEqualTo(userDtoList);
    }

    @Test
    void getUsersById_Test() {
        //Arrange
        doReturn(Optional.ofNullable(user)).when(repositoryUser).findUserById(1L);

        //Act
        Optional<UserDto> result = classUnderTest.getUserById(1L);

        //Assert
        Assertions.assertThat(result).isEqualTo(Optional.ofNullable(userDto));
    }
}