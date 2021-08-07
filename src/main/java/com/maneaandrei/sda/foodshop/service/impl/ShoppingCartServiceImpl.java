package com.maneaandrei.sda.foodshop.service.impl;


import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.service.ShoppingCartService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private Map<Food, Integer> cart = new LinkedHashMap<>();

    @Override
    public void addProduct(Food food)
    {
        if (cart.containsKey(food))
        {
            cart.replace(food, cart.get(food) + 1);
        } else
        {
            cart.put(food, 1);
        }
    }

    @Override
    public void removeProduct(Food food)
    {
        if (cart.containsKey(food))
        {
            if (cart.get(food) > 1)
                cart.replace(food, cart.get(food) - 1);
            else if (cart.get(food) == 1)
            {
                cart.remove(food);
            }
        }
    }

    @Override
    public void clearProducts()
    {
        cart.clear();
    }

    @Override
    public Map<Food, Integer> productsInCart()
    {
        return Collections.unmodifiableMap(cart);
    }

    @Override
    public BigDecimal totalPrice()
    {
        return cart.entrySet().stream()
                .map(k -> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cartCheckout()
    {
        cart.clear();
        // Normally there would be payment etc.
    }
}
