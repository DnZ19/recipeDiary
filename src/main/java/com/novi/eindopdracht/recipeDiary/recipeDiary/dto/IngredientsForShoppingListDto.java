package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
import java.util.List;

public class IngredientsForShoppingListDto {

    @JsonIgnore
    public Long ingredientId;
    public String ingredientName;
    public float quantity;
    public String unit;
    @JsonIgnore
    public Long recipeId;
    @JsonIgnore
    public List<ShoppingList> shoppingList;

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

}
