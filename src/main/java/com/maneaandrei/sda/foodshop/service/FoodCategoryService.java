package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.FoodCategory;
import com.maneaandrei.sda.foodshop.repository.FoodCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }


    public List<FoodCategory> findAll() {
        return StreamSupport.stream(foodCategoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
