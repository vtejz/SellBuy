package com.stackroute.favouriteService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Product already exists")
public class ProductAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

     public ProductAlreadyExists (String message) { super(message); }

}