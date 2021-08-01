package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.repository.FoodCategoryRepository;
import com.maneaandrei.sda.foodshop.repository.FoodRepository;
import com.maneaandrei.sda.foodshop.service.dto.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, FoodCategoryRepository foodCategoryRepository) {
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public void save(FoodDTO foodDTO) {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(foodDTO.getPrice());
        food.setCurrency(foodDTO.getCurrency());
        food.setThumbnail(foodDTO.getThumbnail());
        food.setFoodCategory(foodCategoryRepository.findById(foodDTO.getFoodCategoryId()).orElse(null));

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
