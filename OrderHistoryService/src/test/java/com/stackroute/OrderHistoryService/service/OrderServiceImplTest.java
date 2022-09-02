package com.stackroute.OrderHistoryService.service;

import com.stackroute.OrderHistoryService.controller.OrderController;
import com.stackroute.OrderHistoryService.model.Order;
import com.stackroute.OrderHistoryService.repository.OrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public  class OrderServiceImplTest {
@InjectMocks
    OrderServiceImpl orderService;
@Mock
    OrderRepo orderRepo;

@Test
    public void addProductsThenREturnproductsAdded(){
    Order order=new Order();
    order.setOrderId("123");
    Mockito.when(orderRepo.save(Mockito.any(Order.class))).thenReturn(order);
    boolean expected=true;
    boolean actual=orderService.addOrder(order);
    assertEquals(expected,actual);
}

@Test
    public void viewOrderThenReturnOrder(){
    Order order=new Order();
    order.setOrderId("12");
    List<Order> orderList= Collections.singletonList(order);
    List<Order> expected=orderList;
    Mockito.when(orderRepo.findAll()).thenReturn(orderList);
    List<Order> actual=orderService.viewAll();
    assertEquals(expected,actual);
}
}