package com.stackroute.productService.Exception;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
