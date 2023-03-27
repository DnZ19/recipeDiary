package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nutrition_details")
public class NutritionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long nutritionDetailsId;

    private int calories;
    private int fat;
    private  int protein;
    private  int carbs;

    @OneToOne(mappedBy = "nutritionDetails")
    private Recipe recipe;

    public Long getNutritionDetailsId() {
        return nutritionDetailsId;
    }

    public void setNutritionDetailsId(Long nutritionDetailsId) {
        this.nutritionDetailsId = nutritionDetailsId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
