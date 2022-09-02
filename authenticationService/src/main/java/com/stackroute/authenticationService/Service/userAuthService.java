package com.stackroute.authenticationService.Service;

import com.stackroute.authenticationService.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationService.Exception.UserNotFoundException;
import com.stackroute.authenticationService.Model.User;

public interface userAuthService {
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    //boolean saveUser(User user) throws UserAlreadyExistsException;
}
