package com.maneaandrei.sda.foodshop.repository;

import com.maneaandrei.sda.foodshop.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food,Long> {
}
