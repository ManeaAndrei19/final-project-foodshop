package com.maneaandrei.sda.foodshop.model;

import javax.persistence.*;

@Entity
@Table(name = "food_category")
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String foodCategoryName;

    public FoodCategory(Long id, String foodCategoryName) {
        this.id = id;
        this.foodCategoryName = foodCategoryName;
    }

    public FoodCategory() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }
}
