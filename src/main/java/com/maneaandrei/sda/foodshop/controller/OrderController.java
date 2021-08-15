package com.maneaandrei.sda.foodshop.controller;

import com.maneaandrei.sda.foodshop.service.FoodService;
import com.maneaandrei.sda.foodshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final FoodService foodService;

    public OrderController(OrderService orderService, FoodService foodService) {
        this.orderService = orderService;
        this.foodService = foodService;
    }

    @GetMapping("/view-order")
    public String order(Model model) {
        model.addAttribute("orders",orderService.findAll());

        return "view-order";
    }


//    @GetMapping("/view-order/{id}")
//    public String order(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("orders",orderService.findAllByCustomerId(id));
//
//        return "view-order";
//    }
}