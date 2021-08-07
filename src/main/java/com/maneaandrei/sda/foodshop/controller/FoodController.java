package com.maneaandrei.sda.foodshop.controller;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.model.FoodCategory;
import com.maneaandrei.sda.foodshop.service.FoodCategoryService;
import com.maneaandrei.sda.foodshop.service.FoodService;
import com.maneaandrei.sda.foodshop.service.dto.FoodDTO;
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
    private final FoodCategoryService foodCategoryService;

    public FoodController(FoodService foodService, FoodCategoryService foodCategoryService) {
        this.foodService = foodService;
        this.foodCategoryService = foodCategoryService;
    }

    @GetMapping("/foods")
    public String showFoodsPage(Model model) {
        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);

        List<FoodCategory> food_categories = foodService.findAll().stream()
                .map(food -> food.getFoodCategory()).distinct().collect(Collectors.toList());
        model.addAttribute("food_categories", food_categories);

        return "foods";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable(value = "id") Long id, Model model) {
        foodService.delete(id);

        List<Food> foods = foodService.findAll();
        model.addAttribute("foods", foods);

        return "redirect:/foods";
    }

    @GetMapping("/add-food")
    public String showAddFoodPage(Model model) {
        List<FoodCategory> food_categories = foodCategoryService.findAll();
        model.addAttribute("food_categories", food_categories);

        model.addAttribute("food", new FoodDTO());

        return "add-food";
    }

    @PostMapping("/add-food")
    public String addFood(@Valid FoodDTO foodDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Food> foods = foodService.findAll();
            model.addAttribute("foods", foods);

            List<FoodCategory> food_categories = foodCategoryService.findAll();
            model.addAttribute("food_categories", food_categories);
            return "add-food";
        } else {
            foodService.save(foodDTO);
        }

        return "redirect:/foods";
    }


    ////////EDIT
    @GetMapping("/edit/{id}")
    public String showEditResidenceForm(@PathVariable(value = "id") Long id, Model model) {
        Food food = foodService.findById(id).get(); // convert to DTO
        FoodDTO foodDTO = foodService.createFoodDTO(food);
        model.addAttribute("food", foodDTO); // insert foodDTO?

        List<FoodCategory> food_categories = foodCategoryService.findAll();
        model.addAttribute("food_categories", food_categories);

        return "edit-food";
    }

    @PostMapping("/edit/{id}")
    public String updateResidence(@PathVariable(value = "id") Long id, @Valid FoodDTO foodDTO,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-food";
        }
        foodService.update(foodDTO);


        return "redirect:/foods";
    }

}