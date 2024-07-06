package com.springdatajpa.service;

import com.springdatajpa.Dto.Order.OrderDTO;

public interface OrderService {
    // CreateOrder wiht Address
    OrderDTO createOrder(OrderDTO orderDTO);

    // UpdateOrder with Address
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);
}
