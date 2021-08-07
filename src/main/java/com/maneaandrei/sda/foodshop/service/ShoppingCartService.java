package com.maneaandrei.sda.foodshop.service;

import com.maneaandrei.sda.foodshop.model.Food;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {
    void addProduct(Food food);

    void removeProduct(Food food);

    void clearProducts();

    Map<Food, Integer> productsInCart();

    BigDecimal totalPrice();

    void cartCheckout();
}
