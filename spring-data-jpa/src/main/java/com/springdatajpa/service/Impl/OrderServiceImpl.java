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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // Buscar la orden en la base de datos por su ID
        Optional<Order> searchOrder = orderRepository.findById(orderId);

        // Si la orden existe en la base de datos
        if (searchOrder.isPresent()) {
            // Obtener los datos actuales de la orden desde la base de datos
            Order existingOrder = searchOrder.get();

            // Actualizar los campos de la orden con los datos proporcionados en orderDTO
            existingOrder.setOrderTrackingNumber(orderDTO.getOrderTrackingNumber());
            existingOrder.setTotalQuantity(orderDTO.getTotalQuantity());
            existingOrder.setTotalPrice(orderDTO.getTotalPrice());
            existingOrder.setStatus(orderDTO.getStatus());

            // Obtener los datos de la dirección relacionados con la orden
            AddressDTO addressDTO = orderDTO.getAddress();
            // Si se proporcionaron datos de la dirección en orderDTO
            if (addressDTO != null) {
                // Obtener los datos actuales de la dirección desde la orden
                Address existingAddress = existingOrder.getAddress();
                if (existingAddress != null) {
                    // Actualizar los datos de la dirección existente
                    existingAddress.setStreet(addressDTO.getStreet());
                    existingAddress.setState(addressDTO.getState());
                    existingAddress.setCountry(addressDTO.getCountry());
                    existingAddress.setZipCode(addressDTO.getZipCode());
                } else {
                    // Crear una nueva dirección si no existe
                    Address newAddress = modelMapper.map(addressDTO, Address.class);
                    existingOrder.setAddress(newAddress);
                }
            }
            // Guardar la orden actualizada en la base de datos
            Order updatedOrder = orderRepository.save(existingOrder);
            // Devolver el objeto DTO de la orden actualizada
            return modelMapper.map(updatedOrder, OrderDTO.class);
        } else {
            // Manejar el caso en el que la orden no existe
            // Se puede lanzar una excepción o devolver un valor específico
            return null;
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        // Obtener todas las órdenes desde el repositorio
        List<Order> orders = orderRepository.findAll();

        // Mapear cada orden a OrderDTO y recolectar en una lista
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());

        // Devolver la lista de OrderDTO
        return orderDTOs;
    }

}
