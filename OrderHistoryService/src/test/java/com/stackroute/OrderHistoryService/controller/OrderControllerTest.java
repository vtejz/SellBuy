package com.stackroute.OrderHistoryService.controller;

import com.stackroute.OrderHistoryService.model.Order;
import com.stackroute.OrderHistoryService.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderControllerTest {

    @Mock
    OrderServiceImpl orderService;
    @Mock
    Order order;
    @InjectMocks
    OrderController orderController;

    @Test
    public void shouldViewOrderById(){
        order.setOrderId("12");
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);

        Mockito.when(orderService.viewAll()).thenReturn(orderList);
        ResponseEntity<?> Expected = new ResponseEntity<>(orderList, HttpStatus.OK);
        ResponseEntity<?> Actual = orderController.getOrderHistory();
        Assertions.assertEquals(Expected,Actual);

    }

    @Test
    void shouldAddOrder(){
        order.setOrderId("123");
        Mockito.when(orderService.addOrder(order)).thenReturn(true);
        ResponseEntity<?> Expected = new ResponseEntity<>(order, HttpStatus.CREATED);
        ResponseEntity<?> Actual = orderController.addOrder(order);
        Assertions.assertEquals(Expected,Actual);
    }


}