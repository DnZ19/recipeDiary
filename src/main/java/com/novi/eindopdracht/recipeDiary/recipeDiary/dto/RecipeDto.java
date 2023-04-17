package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;

import java.util.List;

public class RecipeDto {

    public Long recipeId;
    public String name;
    public String instructions;
    public String prepTime;
    public int servings;
    public String notes;
    public String tag;
    public int rating;
    public String recipeSource;
    public String categoryName;

    @JsonIgnore
    public List<Ingredient> ingredients;
    @JsonIgnore
    public NutritionDetails nutritionDetails;
    @JsonIgnore
    public Long shoppingListId;
    @JsonIgnore
    public RecipeDiary recipeDiary;

    @JsonIgnore
    public String errorMessage;

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

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public void setNutritionDetails(NutritionDetails nutritionDetails) {
        this.nutritionDetails = nutritionDetails;
    }

    public RecipeDiary getRecipeDiary() {
        return recipeDiary;
    }

    public void setRecipeDiary(RecipeDiary recipeDiary) {
        this.recipeDiary = recipeDiary;
    }

}
