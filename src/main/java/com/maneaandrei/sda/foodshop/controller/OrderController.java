package com.maneaandrei.sda.foodshop.controller;

import com.maneaandrei.sda.foodshop.service.FoodService;
import com.maneaandrei.sda.foodshop.service.OrderService;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final FoodService foodService;

    public OrderController(OrderService orderService, FoodService foodService) {
        this.orderService = orderService;
        this.foodService = foodService;
    }

    @GetMapping("/orders")
    public String showOrdersPage(Model model) {
        model.addAttribute("orders", orderService.findAll());

        return "orders";
    }


    @GetMapping("/view-order")
    public String showMyOrdersPage(Model model, Principal principal) {
        System.out.println(principal.getName());
        model.addAttribute("orders", orderService.findAllByCustomerLogin(principal.getName()));
        return "view-order";
    }


}

