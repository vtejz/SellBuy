package com.stackroute.favouriteService.service;

import com.stackroute.favouriteService.exception.ProductAlreadyExists;
import com.stackroute.favouriteService.model.Favourite;
import com.stackroute.favouriteService.model.Product;

import java.util.List;

public interface FavService {
    Favourite addFav(Favourite favObj) throws ProductAlreadyExists;
    List<Favourite> viewFav();
    boolean deleteFav(String id);
    boolean deleteProducts(String favName, int id);

    List<Product> getAllProductsByUserId(String userId);



    Product getProductByProductId(String userId,int id);

}
