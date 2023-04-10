package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;

public class NutritionDetailsDto {

    public Long nutritionDetailsId;
    public int calories;
    public int fat;
    public  int protein;
    public  int carbs;
    @JsonIgnore
    public String errorMessage;
    @JsonIgnore
    public Recipe recipe;

    public void setNutritionDetailsId(Long nutritionDetailsId) {
        this.nutritionDetailsId = nutritionDetailsId;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public Long getNutritionDetailsId() {
        return nutritionDetailsId;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
