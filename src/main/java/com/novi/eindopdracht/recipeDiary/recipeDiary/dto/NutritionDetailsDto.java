package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

public class NutritionDetailsDto {

    public Long nutritionDetailsId;

    public int calories;
    public int fat;
    public  int protein;
    public  int carbs;
    public String errorMessage;

    // public Long recipeId;


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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
