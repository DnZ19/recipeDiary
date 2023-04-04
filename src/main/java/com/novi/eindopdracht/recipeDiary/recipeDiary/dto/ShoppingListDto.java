package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;

import java.util.List;

public class ShoppingListDto {

    public Long shoppingListId;
    public String shoppingListName;
    @JsonIgnore
    public Recipe recipe;
    @JsonIgnore
    public Long recipeId;
    public List<IngredientsForShoppingListDto> ingredients;
    @JsonIgnore
    public Long ingredientId;

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<IngredientsForShoppingListDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsForShoppingListDto> ingredients) {
        this.ingredients = ingredients;
    }

    public Long getIngredientId() {
        return ingredientId;
    }
}
