package com.stackroute.UserService.service;

import com.stackroute.UserService.Exception.UserAlreadyExistsException;
import com.stackroute.UserService.Exception.UserNotFoundException;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Repository.UserRepository;
import com.stackroute.UserService.Service.UserService;
import com.stackroute.UserService.Service.UserServiceImpl;
//import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void shouldReturnUser() throws UserAlreadyExistsException {
        User user= new User();
        user.setUserId("sample");

        Mockito.when(userRepository.existsById(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User ExpectedResp= user;
        User ActualResp= userService.registerUser(user);

        Assertions.assertEquals(ExpectedResp,ActualResp);

    }

    @Test
    void shouldBeAbleToViewUserByType() throws UserNotFoundException {
        User user =new User();
        List<User> user1 =new ArrayList<>();
        user.setType("Seller");
        user1.add(user);

        Mockito.when(userRepository.findAllByType(Mockito.anyString())).thenReturn(user1);

        List<User> ExpectedResp= user1;
        List<User> ActualResp=userService.viewUserByType("buyer");

        Assertions.assertEquals(ExpectedResp,ActualResp);
    }

    @Test
    void shouldBeAbleToViewUserById() throws UserNotFoundException {
        User user =new User();
        user.setUserId("23");

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));

        User ExpectedResp= user;
        User ActualResp=userService.viewUserById("12");

        Assertions.assertEquals(ExpectedResp,ActualResp);
    }

    @Test
    void shouldBeAbleToDeleteUser() throws UserNotFoundException {
        User user= new User();
        user.setUserId("24");

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));


        userService.deleteUser("23");
       Mockito.verify(userRepository,Mockito.times(1)).deleteById(Mockito.anyString());


    }

    @Test
    void shouldBeAbleToUpdateUser() throws UserNotFoundException {
        User user= new User();
        user.setUserId("23");
        user.setUserName("Karan");
        user.setPhone("3646582911");
        user.setEmail("nmh@gmail.com");
        user.setPassword("hsdj@jdo");
        user.setAddress("Kol");
        user.setType("buyer");

        User user1= new User();
        user1.setUserId("23");
        user1.setUserName("Karan");
        user1.setPhone("3646583211");
        user1.setEmail("nmh@gmail.com");
        user1.setPassword("hsdj@jdo");
        user1.setAddress("Kol");
        user1.setType("seller");

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));

        User ExpectedResp = user;
        User ActualResponse = userService.updateUser(user1,"23");

        Assertions.assertEquals(ExpectedResp,ActualResponse);


    }
}