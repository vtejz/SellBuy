package com.stackroute.productService.Repository;

import com.stackroute.productService.Model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Products,Integer> {
    Products findByStatus(String status);

//   Products findByCategory();////check
}
