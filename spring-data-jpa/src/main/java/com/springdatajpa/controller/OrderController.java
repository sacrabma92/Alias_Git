package com.springdatajpa.controller;

import com.springdatajpa.Dto.Order.OrderDTO;
import com.springdatajpa.entity.Order;
import com.springdatajpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable(name = "id") Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    @GetMapping
    public List<OrderDTO> getAllOrder(){
        return orderService.getAllOrders();
    }
}
