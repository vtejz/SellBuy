package com.stackroute.productService.Service;
import com.stackroute.productService.Exception.ProductAlreadyExistsException;
import com.stackroute.productService.Exception.ProductNotFoundException;
import com.stackroute.productService.Model.Products;
import com.stackroute.productService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceClass implements ProductServiceInterface{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Products addProducts(Products products) throws ProductAlreadyExistsException {

        Optional<Products> productPresent= productRepository.findById(products.getProductId());
        if(!(productPresent.isPresent())){

            Products productsAdded = productRepository.save(products);
            return productsAdded;

        }else
        {
            throw new ProductAlreadyExistsException("Product Already Exists!");
        }

    }

    @Override
    public List<Products> viewAll() {
        return productRepository.findAll();
    }


    @Override
    public Products viewByStatus(String status) throws ProductNotFoundException {
        Products productsByStatus =productRepository.findByStatus(status);
        if(productsByStatus!=null)
            return productsByStatus;
        else
            throw new ProductNotFoundException("Product With"+status+" does not Exist!");
    }
//    @Override
//    public Products viewByCategory() throws ProductNotFoundException {
//        Products productsByCategory =productRepository.findByCategory();
//        if(productsByCategory!=null)
//            return productsByCategory;
//        else
//            throw new ProductNotFoundException("Product With given does not Exist!");
//    }

    @Override
    public Products viewById(Integer id) throws ProductNotFoundException {
        Optional<Products> productsById =productRepository.findById(id);
        if(productsById.isPresent())
            return productsById.get();
        else
            throw new ProductNotFoundException("Product With given"+id +"does not Exist!");
    }

    @Override
    public void deleteById(Integer id) throws ProductNotFoundException {
        boolean producttoDelete= productRepository.existsById(id);
        if(producttoDelete)
            productRepository.deleteById(id);
        else
            throw new ProductNotFoundException("Product With given"+id +"does not Exist!");
    }

    @Override
    public Products updateById(Products product, Integer id) throws ProductNotFoundException {
        System.out.println("Inside Update Id");
       if( productRepository.existsById(id)){
           Products producttoUpdate=productRepository.findById(id).get();
            return productRepository.save(producttoUpdate);
        }
       else
            throw new ProductNotFoundException("Product Does not Exists!");
    }
}
