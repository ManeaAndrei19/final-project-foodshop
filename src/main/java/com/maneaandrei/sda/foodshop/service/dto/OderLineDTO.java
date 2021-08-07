package com.maneaandrei.sda.foodshop.service.dto;

import com.maneaandrei.sda.foodshop.model.Food;
import com.maneaandrei.sda.foodshop.model.Order;


public class OderLineDTO {
    private Long id;
    private Food food;
    private Integer quantity;
    private Double price;
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
