package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long customerId);
}
