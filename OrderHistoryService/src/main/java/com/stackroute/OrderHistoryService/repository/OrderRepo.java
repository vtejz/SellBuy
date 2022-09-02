package com.stackroute.OrderHistoryService.repository;

import com.stackroute.OrderHistoryService.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends MongoRepository<Order,String> {
}
