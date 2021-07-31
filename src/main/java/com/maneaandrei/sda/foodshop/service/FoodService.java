package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void save(Food food) {
        foodRepository.save(food);
    }

    public List<Food> findAll() {
        return StreamSupport.stream(foodRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<Food> findById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    public void delete(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isPresent()) {
            foodRepository.delete(food.get());
        } else {
            throw new IllegalArgumentException("Food not found");
        }
    }
}
