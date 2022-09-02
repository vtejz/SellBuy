package com.stackroute.productService.Service;

import com.stackroute.productService.Exception.ProductAlreadyExistsException;
import com.stackroute.productService.Exception.ProductNotFoundException;
import com.stackroute.productService.Model.Products;
import com.stackroute.productService.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class ProductServiceClassTest {
    @InjectMocks
    ProductServiceClass productserviceClass;
    @Mock
    ProductRepository productRepository;

    @Test
    void addProductShouldReturnProductadded() throws ProductAlreadyExistsException {
        Products products = new Products();
        products.setProductId(1);
        Mockito.when(productRepository.save(Mockito.any(Products.class))).thenReturn(products);
        Products expectedResponse= products;
        Products actualResponse = productserviceClass.addProducts(products);
        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void addProductsShouldThrowExceptionWhenProductsAlreadyExists()
    {
        Products products = new Products();
        products.setProductId(1);
        Products expectedResponse = products;
        Mockito.when(productRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(products));

        assertThatThrownBy(()->productserviceClass.addProducts(products))
                .isInstanceOf(ProductAlreadyExistsException.class);
    }

    @Test
    void ViewAllProductsShouldReturnAListOfProducts()
    {
        Products products = new Products();
        products.setProductId(1);
        List<Products> productsList = Collections.singletonList(products);
        List<Products> expectedResponse = productsList;
        Mockito.when(productRepository.findAll()).thenReturn(productsList);
        List<Products> actualResponse = productserviceClass.viewAll();
        assertEquals(expectedResponse,actualResponse);
    }


    @Test
    void ViewByStatusShouldReturnAProductsOfGivenStatus() throws ProductNotFoundException {
        Products products = new Products();
        products.setStatus("Status");
        Mockito.when(productRepository.findByStatus(Mockito.anyString()))
                .thenReturn(products);
        Products expectedResponse = products;
        Products actualResponse= productserviceClass.viewByStatus(products.getStatus());
       Assertions.assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void ViewByIdShouldReturnAOfProductOfGivenId() throws ProductNotFoundException {
        Products products = new Products();
        products.setProductId(1);
        Mockito.when(productRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(products));
        Products expectedResponse = products;
        Products actualResponse= productserviceClass.viewById(products.getProductId());
        Assertions.assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void DeleteByIdShouldBeAbleToDeleteProduct() throws ProductNotFoundException {
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
        productserviceClass.deleteById(1);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void UpdateShouldBeAbleToUpdateTheProductAndReturnAProducts() throws ProductNotFoundException {
        Products products = new Products();
        products.setProductId(1);
        Mockito.when(productRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(products));
        Mockito.when(productRepository.save(products)).thenReturn(products);
        Products expected= products;
        Products actual = productserviceClass.updateById(products,products.getProductId());
        assertEquals(expected,actual);
    }


}
