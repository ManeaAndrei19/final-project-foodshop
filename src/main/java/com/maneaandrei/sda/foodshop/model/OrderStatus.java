package com.maneaandrei.sda.foodshop.model;

public enum OrderStatus {
    NEW("New Order"),
    CONFIRMED("Order is being prepared"),
    PENDING("Pending Delivery"),
    DELIVERY("Order is being delivered"),
    DELIVERED("Order completed");

    private final String displayValue;

    OrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
