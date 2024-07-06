package com.springdatajpa.service;

import com.springdatajpa.Dto.Order.OrderDTO;

import java.util.List;

public interface OrderService {
    // CreateOrder wiht Address
    OrderDTO createOrder(OrderDTO orderDTO);

    // UpdateOrder with Address
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);

    // Obtener todas las ordenes
    List<OrderDTO> getAllOrders();
}
