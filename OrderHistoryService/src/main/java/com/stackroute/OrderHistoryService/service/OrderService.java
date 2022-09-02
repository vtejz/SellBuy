package com.stackroute.OrderHistoryService.service;

import com.stackroute.OrderHistoryService.model.Order;

import java.util.List;

public interface OrderService {
       boolean addOrder(Order order);
       Order getOrderById(String orderId);
       List<Order> viewAll();
}
