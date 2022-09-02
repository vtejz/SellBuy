package com.stackroute.productService.Controller;

import com.stackroute.productService.Exception.ProductAlreadyExistsException;
import com.stackroute.productService.Exception.ProductNotFoundException;
import com.stackroute.productService.Model.Products;
import com.stackroute.productService.Service.ProductServiceClass;
import com.stackroute.productService.Service.ProductServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {
    @Autowired
    ProductServiceInterface productService;

    //Add the products
    @ApiOperation("To Add Products")
    @PostMapping("add")
    public ResponseEntity<?> addingProducts(@RequestBody Products products) {
        try {
            //System.out.println("Inside add prd");
            Products productsadded = productService.addProducts(products);

            return new ResponseEntity<Products>(productsadded, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //ViewAll products
    @ApiOperation("To ViewAll products")
    @GetMapping("viewall")
    public ResponseEntity<?> viewAll() {
        List<Products> Allproducts = productService.viewAll();
        return new ResponseEntity<>(Allproducts, HttpStatus.FOUND);
    }

    //To view the products by Status
    @ApiOperation("To view the products by Status")
    @GetMapping("viewstatus/{Status}")
    public ResponseEntity<?> viewByStatus(@PathVariable String Status)
    {
        try{
            Products productspresent = productService.viewByStatus(Status);
            return new ResponseEntity<>(productspresent, HttpStatus.FOUND);
        }
        catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //view By ProductId
    @ApiOperation("To view By ProductId")
    @GetMapping("view/{Id}")
    public ResponseEntity<?> viewById(@PathVariable int Id)
    {
        try{
            Products productswithId = productService.viewById(Id);
            return new ResponseEntity<>(productswithId, HttpStatus.FOUND);
        }
        catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // To Delete the products by Id
    @ApiOperation("To Delete the products by Id")
    @DeleteMapping("delete/{Id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer Id)
    {
        try{
            productService.deleteById(Id);
            return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
        }
        catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //To Update the product by Id
    @ApiOperation("To Update the product by Id")
    @PutMapping("update/{Id}")
    public ResponseEntity<?> updateById(@RequestBody Products product, @PathVariable("Id") Integer Id){
        try{
            Products productupdated=productService.updateById(product,Id);
            return new ResponseEntity<>(productupdated, HttpStatus.OK);
        }
        catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
