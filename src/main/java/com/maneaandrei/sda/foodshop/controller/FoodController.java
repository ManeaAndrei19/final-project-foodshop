package com.maneaandrei.sda.foodshop.controller;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.model.FoodCategory;
import com.maneaandrei.sda.foodshop.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public String showFoodsPage(Model model) {
        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);

        List<FoodCategory> food_categories = foodService.findAll().stream()
                .map(food -> food.getFoodCategory()).collect(Collectors.toList());
        model.addAttribute("food_categories", food_categories);

        return "foods";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable(value = "id") Long id, Model model) {
        foodService.delete(id);

        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);

        return "foods";
    }

    @GetMapping("/add-food")
    public String showAddFoodPage(Food food) {
        return "add-food";
    }

    @PostMapping("/add-food")
    public String addFood(@Valid Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-food";
        } else {
            foodService.save(food);
        }

        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);

        return "foods";
    }
}
