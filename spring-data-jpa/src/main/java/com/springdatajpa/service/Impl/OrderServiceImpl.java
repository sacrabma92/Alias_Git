package com.springdatajpa.service.Impl;

import com.springdatajpa.Dto.Address.AddressDTO;
import com.springdatajpa.Dto.Order.OrderDTO;
import com.springdatajpa.entity.Address;
import com.springdatajpa.entity.Order;
import com.springdatajpa.exceptions.ResourceNotFoundException;
import com.springdatajpa.repository.OrderRepository;
import com.springdatajpa.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO, Order.class);
        Order saveOrder = orderRepository.save(order);
        return modelMapper.map(saveOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            // Update Order fields
            existingOrder.setOrderTrackingNumber(orderDTO.getOrderTrackingNumber());
            existingOrder.setTotalQuantity(orderDTO.getTotalQuantity());
            existingOrder.setTotalPrice(orderDTO.getTotalPrice());
            existingOrder.setStatus(orderDTO.getStatus());

            AddressDTO addressDTO = orderDTO.getAddress();
            if (addressDTO != null) {
                Address existingAddress = existingOrder.getAddress();
                if (existingAddress != null) {
                    // Update existing address fields
                    existingAddress.setStreet(addressDTO.getStreet());
                    existingAddress.setState(addressDTO.getState());
                    existingAddress.setCountry(addressDTO.getCountry());
                    existingAddress.setZipCode(addressDTO.getZipCode());
                } else {
                    // Set new address if it does not exist
                    Address newAddress = modelMapper.map(addressDTO, Address.class);
                    existingOrder.setAddress(newAddress);
                }
            }

            Order updatedOrder = orderRepository.save(existingOrder);
            return modelMapper.map(updatedOrder, OrderDTO.class);
        } else {
            // Handle the case where the order doesn't exist
            // You can throw an exception or return a specific value
            return null;
        }
    }
}
