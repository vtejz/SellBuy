package com.stackroute.OrderHistoryService.controller;

import com.stackroute.OrderHistoryService.model.Order;
import com.stackroute.OrderHistoryService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        boolean isOrderExist= orderService.addOrder(order);
        if (isOrderExist==true){
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("viewOrder/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") String orderId){
        Order orderById=orderService.getOrderById(orderId);
        return  new ResponseEntity<>(orderById,HttpStatus.OK);

    }

    @GetMapping("viewAll")
    public ResponseEntity<?>getOrderHistory(){
        List<Order> orderHist= orderService.viewAll();
        return new ResponseEntity<List>(orderHist,HttpStatus.OK);
    }
}
