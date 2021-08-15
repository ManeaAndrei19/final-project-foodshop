package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Order;
import com.maneaandrei.sda.foodshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> findAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Order> findAllByCustomerId(Long customerId) {
        return StreamSupport.stream(orderRepository.findAllByCustomerId(customerId).spliterator(), false).collect(Collectors.toList());
    }
}
