package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RecipeDto {

    public Long recipeId;

    @NotBlank
    public String name;
    @NotBlank
    public String instructions;

    public int prepTime;
    public int servings;

    @Size(min=3, max=255)
    public String notes;
    public List<String> photos;
    public List<String> tags;
    public int rating;
    public String recipeSource;
    public String categoryName;

}
