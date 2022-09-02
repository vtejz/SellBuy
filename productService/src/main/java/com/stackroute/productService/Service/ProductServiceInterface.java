package com.stackroute.productService.Service;

import com.stackroute.productService.Exception.ProductAlreadyExistsException;
import com.stackroute.productService.Exception.ProductNotFoundException;
import com.stackroute.productService.Model.Products;

import java.util.List;

public interface ProductServiceInterface {
    Products addProducts(Products products) throws ProductAlreadyExistsException;

    List<Products> viewAll();

    Products viewByStatus(String status) throws ProductNotFoundException;

//    Products viewByCategory() throws ProductNotFoundException;

    Products viewById(Integer id) throws ProductNotFoundException;

    void deleteById(Integer id) throws ProductNotFoundException;

    Products updateById(Products product, Integer id) throws ProductNotFoundException;
}
