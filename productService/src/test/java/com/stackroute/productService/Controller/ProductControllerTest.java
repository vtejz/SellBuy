package com.stackroute.productService.Controller;

import com.stackroute.productService.Exception.ProductAlreadyExistsException;
import com.stackroute.productService.Exception.ProductNotFoundException;
import com.stackroute.productService.Model.Products;
import com.stackroute.productService.Service.ProductServiceClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductControllerTest {

    @Mock
    ProductServiceClass productServiceClass;

    @Mock
    Products product;

    @InjectMocks
    ProductController productController;


    @Test
    void shouldViewProductById() throws ProductNotFoundException {
        product.setProductId(1);
        Mockito.when(productServiceClass.viewById(Mockito.anyInt())).thenReturn(product);
        ResponseEntity<?> Expected = new ResponseEntity<>(product, HttpStatus.FOUND);
        ResponseEntity<?> Actual = productController.viewById(product.getProductId());
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    void ShouldViewProductByStatus() throws ProductNotFoundException{
        product.setStatus("Sample");
        Mockito.when(productServiceClass.viewByStatus(Mockito.anyString())).thenReturn(product);
        ResponseEntity<?> Expected = new ResponseEntity<>( HttpStatus.FOUND);
        ResponseEntity<?> Actual = productController.viewByStatus(product.getStatus());
        Assertions.assertEquals(Expected,Actual);
    }
    @Test
    void shouldDeleteProductById() throws ProductNotFoundException {
        product.setProductId(1);
        productController.deleteById(1);
        Mockito.verify(productServiceClass,Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void shouldUpdateProduct() throws ProductNotFoundException{
        product.setProductId(1);
        product.setProductPrice(123);
        Mockito.when(productServiceClass.updateById(any(),(Mockito.anyInt()))).thenReturn(product);
        ResponseEntity<?> Expected = new ResponseEntity<>(product, HttpStatus.OK);
        ResponseEntity<?> Actual = productController.updateById(product,product.getProductId());
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    void shouldAddProduct() throws ProductAlreadyExistsException{
        product.setProductId(1);
        Mockito.when(productServiceClass.addProducts(product)).thenReturn(product);
        ResponseEntity<?> Expected = new ResponseEntity<>(product, HttpStatus.CREATED);
        ResponseEntity<?> Actual = productController.addingProducts(product);
        Assertions.assertEquals(Expected,Actual);
    }
    @Test
    void shouldViewAllProducts() {
        product.setProductId(1);
        List<Products> products = new ArrayList<>();
        products.add(product);
        Mockito.when(productServiceClass.viewAll()).thenReturn(products);
        ResponseEntity<?> Expected = new ResponseEntity<>(products, HttpStatus.FOUND);
        ResponseEntity<?> Actual = productController.viewAll();
        Assertions.assertEquals(Expected,Actual);
    }
}
