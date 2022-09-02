package com.stackroute.UserService.Controller;

import com.stackroute.UserService.Exception.UserAlreadyExistsException;
import com.stackroute.UserService.Exception.UserNotFoundException;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Repository.UserRepository;
import com.stackroute.UserService.Service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserServiceImpl userService;

    @Mock
    User mockUser;

    @InjectMocks
    UserController userController;

    @Test
    void shouldBeAbleToRegisterUser() throws UserAlreadyExistsException {

        User user= new User();
        user.setUserId("6");

        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(user);

        ResponseEntity<?> Expected = new ResponseEntity<>(user, HttpStatus.CREATED);

        ResponseEntity<?> Actual = userController.addUser(user);

        Assertions.assertEquals(Expected,Actual);

    }

    @Test
    void shouldViewUserById() throws UserNotFoundException {

        User user = new User();
        user.setUserId("8");

        Mockito.when(userService.viewUserById(Mockito.anyString())).thenReturn(user);

        ResponseEntity<?> Expected = new ResponseEntity<>(user,HttpStatus.OK);

        ResponseEntity<?> Actual = userController.viewById(user.getUserId());

        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    void shouldViewUserByType() throws UserNotFoundException {

        User user = new User();
        user.setType("Seller");
        List<User> u1 = Collections.singletonList(user);

        Mockito.when(userService.viewUserByType(Mockito.anyString())).thenReturn(u1);

        ResponseEntity<?> Expected = new ResponseEntity<List>( u1,HttpStatus.OK);

        ResponseEntity<?> Actual = userController.viewByType(user.getType());

        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    void shouldDeleteUserById() throws UserNotFoundException {

        User user = new User();
        user.setUserId("8");



        userController.deleteUser("8");
        Mockito.verify(userService,Mockito.times(1)).deleteUser(Mockito.anyString());

    }
}