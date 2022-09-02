package com.stackroute.favouriteService.service;

import com.stackroute.favouriteService.exception.ProductAlreadyExists;
import com.stackroute.favouriteService.model.Favourite;
import com.stackroute.favouriteService.model.Product;
import com.stackroute.favouriteService.repository.FavRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FavServiceImpl implements FavService{

    @Autowired
    FavRepo favRepo;
    @Override
    public Favourite addFav(Favourite favourite) throws ProductAlreadyExists {

        Favourite favResult = null;
        Optional<Favourite> optFav = favRepo.findById(favourite.getUserId());

        if (optFav.isPresent()) {
            for (Product prodExists : optFav.get().getProducts()) {
                for (Product newProd : favourite.getProducts())
                    if (prodExists.getProductId() == newProd.getProductId()) {
                        throw new ProductAlreadyExists("Product already exists");
                    }
            }
        }

        if (optFav.isEmpty()) {
            favResult = favRepo.save(favourite);
        } else {
            Favourite favExist = optFav.get();
            List<Product> produList = favExist.getProducts();
            produList.addAll(favourite.getProducts());
            favExist.setProducts(produList);
            favResult = favRepo.save(favExist);
        }

        return favResult;

    }


    @Override
    public List<Favourite> viewFav() {

        return favRepo.findAll();
    }

    @Override
    public boolean deleteFav(String id) {
        Optional<Favourite> optFav=favRepo.findById(id);
        if(optFav.isPresent()) {
            favRepo.deleteById(id);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteProducts(String favId, int id) {
        Favourite favResult = null;
        Optional<Favourite> optFav = favRepo.findById(favId);
        if(optFav.isPresent()) {
            Favourite favExist =optFav.get();
            List<Product> prodList=favExist.getProducts();
            Iterator<Product> iterator= prodList.iterator();
            while(iterator.hasNext()) {
                if(iterator.next().getProductId()==id)
                    iterator.remove();
            }
            favExist.setProducts(prodList);
            favRepo.save(favExist);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProductsByUserId(String userId) {
        return favRepo.findById(userId).get().getProducts();
    }



    @Override
    public Product getProductByProductId(String userId, int id) {
        Favourite favourite=favRepo.findById(userId).get();
        List<Product> pList=favourite.getProducts();
        for(Product productWithId:pList) {
            if(productWithId.getProductId()==id) {
                return productWithId;
            }
        }
        return null;
    }

}
