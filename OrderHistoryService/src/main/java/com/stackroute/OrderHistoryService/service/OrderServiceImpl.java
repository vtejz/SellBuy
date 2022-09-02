package com.stackroute.OrderHistoryService.service;

import com.stackroute.OrderHistoryService.model.Order;
import com.stackroute.OrderHistoryService.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Override
    public boolean addOrder(Order order) {
        Boolean orderById=orderRepo.existsById(order.getOrderId());
        if (orderById==false){
            Order orderAdded=orderRepo.save(order);
            if (orderAdded!=null)
                return true;
        }
        return false;
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepo.findById(orderId).get();
    }

    @Override
    public List<Order> viewAll() {
        return orderRepo.findAll();
    }
}
