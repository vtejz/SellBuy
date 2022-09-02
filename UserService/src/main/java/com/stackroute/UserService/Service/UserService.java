package com.stackroute.UserService.Service;

import com.stackroute.UserService.Exception.UserAlreadyExistsException;
import com.stackroute.UserService.Exception.UserNotFoundException;
import com.stackroute.UserService.Model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user) throws UserAlreadyExistsException;

   public List<User>  viewUserByType (String type) throws UserNotFoundException;

    public User  viewUserById (String Id) throws UserNotFoundException;

    public User updateUser(User user, String id)  throws UserNotFoundException;

    public List<User> getAllUsers();

    public boolean deleteUser(String id) throws UserNotFoundException;
}
