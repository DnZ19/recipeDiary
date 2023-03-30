package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
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

    @Size(min=3, max=255)
    public String notes;
    public List<String> photos;
    public List<String> tags;
    public int rating;
    public String recipeSource;
    public String categoryName;

    public Long nutritionDetailsId;

    public Long shoppingListId;

    public String errorMessage;


}
