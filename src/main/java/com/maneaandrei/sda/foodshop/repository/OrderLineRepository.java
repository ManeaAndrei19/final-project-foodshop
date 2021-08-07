package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
}
