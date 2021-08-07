package com.maneaandrei.sda.foodshop.controller;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.service.FoodService;
import com.maneaandrei.sda.foodshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final FoodService foodService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, FoodService foodService) {
        this.shoppingCartService = shoppingCartService;
        this.foodService = foodService;
    }

    @GetMapping("/cart")
    public String cart(Model model)
    {
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id)
    {
        Food food = foodService.findById(id).orElse(null);
        if (food != null)
        {
            shoppingCartService.addProduct(food);
        }
        return "redirect:/foods";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id)
    {
        Food food = foodService.findById(id).orElse(null);
        if (food != null)
        {
            shoppingCartService.removeProduct(food);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart()
    {
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout()
    {
        shoppingCartService.cartCheckout();
        return "redirect:/cart";
    }

}
