package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RecipeDto {

    public Long recipeId;

    @NotBlank
    public String name;
    @NotBlank
    public String instructions;
    public String prepTime;
    public int servings;
    public String notes;
    public List<String> photos;
    public List<String> tags;
    public int rating;
    public String recipeSource;
    public String categoryName;

    public List<Ingredient> ingredients;

   // public Long nutritionDetailsId;
    public NutritionDetails nutritionDetails;

    public Long shoppingListId;

    public String errorMessage;

    @JsonIgnore
    public String shoppingListName;
    @JsonIgnore
    public int fat;
    @JsonIgnore
    public int carbs;
    @JsonIgnore
    public int protein;
    @JsonIgnore
    public int calories;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

//    public Long getNutritionDetailsId() {
//        return nutritionDetailsId;
//    }
//
//    public void setNutritionDetailsId(Long nutritionDetailsId) {
//        this.nutritionDetailsId = nutritionDetailsId;
//    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public NutritionDetails getNutritionDetails() {
        return nutritionDetails;
    }

    public void setNutritionDetails(NutritionDetails nutritionDetails) {
        this.nutritionDetails = nutritionDetails;
    }
}
