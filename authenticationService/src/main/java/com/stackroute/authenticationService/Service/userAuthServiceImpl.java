package com.stackroute.authenticationService.Service;

import com.stackroute.authenticationService.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationService.Exception.UserNotFoundException;
import com.stackroute.authenticationService.Model.User;
import com.stackroute.authenticationService.Repository.userAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class userAuthServiceImpl implements userAuthService{
    @Autowired
    RestTemplate restTemplate;
    @Override
    public User findByUserIdAndPassword(String userId, String password)  {

        User user = restTemplate.getForObject("http://UserService/user/internalview/"+userId,User.class);

        if(user == null || !(user.getPassword().equals(password))) return null;

        return user;

    }


}
