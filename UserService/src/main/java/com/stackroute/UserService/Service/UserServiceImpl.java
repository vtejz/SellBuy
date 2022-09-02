package com.stackroute.UserService.Service;

import com.stackroute.UserService.Exception.UserAlreadyExistsException;
import com.stackroute.UserService.Exception.UserNotFoundException;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        Boolean userId= userRepository.existsById(user.getUserId());
        //User type= userRepository.insert(user.getType());
        if(userId==false){

          User user1= userRepository.save(user);
          return user1;

          }

        throw new UserAlreadyExistsException("User already exists in db");
    }

    @Override
    public List<User> viewUserByType(String type) throws UserNotFoundException {
       List<User> u1=userRepository.findAllByType(type);
       if(u1 != null)
       {
           return u1;
       }
       throw new UserNotFoundException("No user with this type");
    }

    @Override
    public User viewUserById(String Id) throws UserNotFoundException {
        User userById= userRepository.findById(Id).get();
        if(userById !=null){
            return  userById;
        }
        throw new UserNotFoundException("No user found");
    }

    @Override
    public User updateUser(User user, String id) throws UserNotFoundException {
        User user1= userRepository.findById(id).get();
        if(user1 != null){
            user1.setUserName(user.getUserName());
            user1.setAddress(user.getAddress());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setType(user.getType());
            userRepository.save(user1);
            User user2= userRepository.findById(id).get();
            return user2;
        }
        throw new UserNotFoundException("No user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(String id) throws UserNotFoundException {
        User user1 = userRepository.findById(id).get();
        if(user1 != null){
            userRepository.deleteById(id);
            return true;
        }
        throw new UserNotFoundException("User does not exist");
    }


}
