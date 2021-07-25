package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
