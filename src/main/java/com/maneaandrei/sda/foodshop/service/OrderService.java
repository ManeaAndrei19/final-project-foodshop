package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Order;
import com.maneaandrei.sda.foodshop.repository.FoodCategoryRepository;
import com.maneaandrei.sda.foodshop.repository.FoodRepository;
import com.maneaandrei.sda.foodshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository, FoodCategoryRepository foodCategoryRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }


    public List<Order> findAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Order> findAllByCustomerId(Long customerId) {
        return StreamSupport.stream(orderRepository.findAllByCustomerId(customerId).spliterator(), false).collect(Collectors.toList());
    }
}
