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
}
