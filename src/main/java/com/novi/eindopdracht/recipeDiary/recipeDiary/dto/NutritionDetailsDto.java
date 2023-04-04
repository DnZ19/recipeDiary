package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

public class NutritionDetailsDto {

    public Long nutritionDetailsId;

    public int calories;
    public int fat;
    public  int protein;
    public  int carbs;
    public String errorMessage;

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

}
