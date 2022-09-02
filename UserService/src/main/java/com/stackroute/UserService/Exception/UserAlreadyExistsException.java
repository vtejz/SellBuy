package com.stackroute.UserService.Exception;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
