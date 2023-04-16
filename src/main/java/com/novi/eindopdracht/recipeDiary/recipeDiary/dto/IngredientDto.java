package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;

import java.util.List;

public class IngredientDto {

    public Long ingredientId;
    public String ingredientName;
    public float quantity;
    public String unit;
    public String categoryName;
    @JsonIgnore
    public String errorMessage;
    public Long recipeId;
    @JsonIgnore
    public List<ShoppingList> shoppingList;

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

//    public Long getIngredientId() {
//        return ingredientId;
//    }
//
//    public String getIngredientName() {
//        return ingredientName;
//    }
//
//    public float getQuantity() {
//        return quantity;
//    }
//
//    public String getUnit() {
//        return unit;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public List<ShoppingList> getShoppingList() {
//        return shoppingList;
//    }
}
