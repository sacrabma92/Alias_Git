package com.springdatajpa.controller;

import com.springdatajpa.Dto.Order.OrderDTO;
import com.springdatajpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }
}
