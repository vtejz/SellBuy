package com.stackroute.authenticationService.Service;

import com.stackroute.authenticationService.Model.User;
import com.stackroute.authenticationService.Repository.userAuthRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class userAuthServiceImplTest {

    @Mock
    userAuthRepo userAuthRepo1;
    @Mock
    RestTemplate restTemplate;
    @Mock
    User mockUser;

    @InjectMocks
    userAuthServiceImpl userAuthService;

    @Test
    void shouldBeAbleToLoginWithUserIdAndPassword(){

        User user = new User();
        user.setUserId("2");
        user.setPassword("hdhbwie");

        Mockito.when(restTemplate.getForObject(Mockito.anyString(),Mockito.any())).thenReturn(user);

        User ExpectedResp= user;
        User ActualResp= userAuthService.findByUserIdAndPassword(user.getUserId(), user.getPassword());

        Assertions.assertEquals(ExpectedResp,ActualResp);
    }



}