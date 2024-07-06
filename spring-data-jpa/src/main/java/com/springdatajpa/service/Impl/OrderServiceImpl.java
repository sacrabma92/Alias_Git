package com.springdatajpa.service.Impl;

import com.springdatajpa.Dto.Order.OrderDTO;
import com.springdatajpa.entity.Order;
import com.springdatajpa.repository.OrderRepository;
import com.springdatajpa.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO createOrder(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO, Order.class);
        Order saveOrder = orderRepository.save(order);
        return modelMapper.map(saveOrder, OrderDTO.class);
    }

}
