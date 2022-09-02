package com.stackroute.UserService.Controller;

import com.stackroute.UserService.Exception.UserAlreadyExistsException;
import com.stackroute.UserService.Exception.UserNotFoundException;
import com.stackroute.UserService.Model.User;
import com.stackroute.UserService.Service.UserService;
import com.stackroute.UserService.Service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){

        try{
                User user1=userService.registerUser(user);
                if(user1 != null)
                {
                    logger.info("In controller - {}", "User Profile created: " +user1);
                    return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }}catch (UserAlreadyExistsException e)
        {
            return new ResponseEntity<User>( HttpStatus.CONFLICT);
        }
        logger.info("In controller - {}", "User ID "+ user.getUserId() + " already exists.");
        return new ResponseEntity<User>(HttpStatus.CONFLICT);
    }

    @GetMapping("/viewType/{type}")
    public ResponseEntity<?> viewByType( @PathVariable("type") String type){
        try{
           List<User> u1 =userService.viewUserByType(type);
           if(u1 != null) {
               return new ResponseEntity<List<User>>(u1, HttpStatus.OK);
           }
        }catch (UserNotFoundException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<User> viewById(@PathVariable("id") String userId){
        try{
           User u1= userService.viewUserById(userId);
           if(u1 != null) {
               logger.info("In controller - {}", "User Profile retrieved for user ID: "+userId+ "is: "+u1);
               return new ResponseEntity<User>(u1, HttpStatus.OK);
           }
        }catch (UserNotFoundException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        logger.info("In controller - {}", "User ID "+ userId + " not found in database.");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/internalview/{id}")
    public ResponseEntity<User> viewByIdInternal(@PathVariable("id") String userId){
        try{
            User u1= userService.viewUserById(userId);
            if(u1 != null) {
                logger.info("In controller - {}", "User Profile retrieved for user ID: "+userId+ "is: "+u1);
                return new ResponseEntity<User>(u1, HttpStatus.OK);
            }
        }catch (UserNotFoundException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        logger.info("In controller - {}", "User ID "+ userId + " not found in database.");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/viewAllUser")
    public ResponseEntity<?> getAllUsers(){
        List<User> user1= userService.getAllUsers();
        return new ResponseEntity<List<User>>(user1,HttpStatus.OK);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<?> updateUser( @RequestBody User user,@PathVariable("id") String userId){
        try {
            User u1=userService.updateUser(user, userId);
            if(u1 != null) {
                logger.info("In controller - {}", "User Profile updated: " +u1);
                return new ResponseEntity<User>(u1, HttpStatus.OK);
            }
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        logger.info("In controller - {}", "User ID "+ userId + " not found in database.");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id){
        try{
            boolean u1=userService.deleteUser(id);
            if(u1 == true) {
                logger.info("In controller - {}", "User Profile deleted for user ID: "+u1);
                return new ResponseEntity<String>(id, HttpStatus.OK);
            }
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        logger.info("In controller - {}", "User ID "+ id + " not found in database.");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

}
