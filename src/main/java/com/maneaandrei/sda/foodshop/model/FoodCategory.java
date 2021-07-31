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

    @Column(name = "hash")
    private String foodHash;

    public FoodCategory(Long id, String foodCategoryName, String foodHash) {
        this.id = id;
        this.foodCategoryName = foodCategoryName;
        this.foodHash = foodHash;
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

    public String getFoodHash() {
        return foodHash;
    }

    public void setFoodHash(String foodHash) {
        this.foodHash = foodHash;
    }
}
