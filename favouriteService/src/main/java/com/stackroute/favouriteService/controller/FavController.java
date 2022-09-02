package com.stackroute.favouriteService.controller;

import com.stackroute.favouriteService.exception.ProductAlreadyExists;
import com.stackroute.favouriteService.model.Favourite;
import com.stackroute.favouriteService.model.Product;
import com.stackroute.favouriteService.service.FavService;
import com.stackroute.favouriteService.service.FavServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class FavController {

    @Autowired
    FavService favService;

    @PostMapping("addfav")
    public ResponseEntity<?> saveFav(@RequestBody Favourite favourite){
        Favourite result;
        try {
            result = favService.addFav(favourite);
            return new ResponseEntity<Favourite>(result,HttpStatus.CREATED);
        } catch (ProductAlreadyExists e) {
            // TODO Auto-generated catch block
            return new ResponseEntity<String>("Product Already exists",HttpStatus.CONFLICT);
        }

    }
    @GetMapping("viewFav")
    public ResponseEntity<?>getFav(){
        List<Favourite> fav= favService.viewFav();
        return new ResponseEntity<List>(fav,HttpStatus.OK);



    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getAllProductsById(@PathVariable("userId") String userId){
        List<Product> allProducts =favService.getAllProductsByUserId(userId);
        if(allProducts!=null) {
            return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{prodId}")
    public ResponseEntity<Product> getProdById(@PathVariable("userId") String userId,@PathVariable("prodId") int prodId){
        Product prodById;
        prodById=favService.getProductByProductId(userId, prodId);
        if(prodById!=null) {
            return new ResponseEntity<Product>(prodById,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity deleteProduct(@PathVariable ("userId") String userId){
          boolean res= favService.deleteFav(userId);
          if (res){
              return new ResponseEntity<>("product deleted",HttpStatus.OK);
          }else
              return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("deleteProd/{userId}/{prodId}")
    public ResponseEntity deleteProduct(@PathVariable("userId") String userId,@PathVariable("prodId") int prodId) {
        boolean res=favService.deleteProducts(userId, prodId);
        if(res) {
            return new ResponseEntity<String>("product deleted",HttpStatus.OK);
        }else
            return new ResponseEntity<String>("product not found",HttpStatus.NOT_FOUND);
    }


}
