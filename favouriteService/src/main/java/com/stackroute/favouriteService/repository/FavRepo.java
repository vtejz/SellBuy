package com.stackroute.favouriteService.repository;

import com.stackroute.favouriteService.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavRepo extends MongoRepository<Favourite, String>{


}

