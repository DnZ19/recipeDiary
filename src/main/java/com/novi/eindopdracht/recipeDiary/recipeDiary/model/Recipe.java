package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "recipes")

public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long recipeId;
    private String name;
    private String instructions;
    private String prepTime;
    private int servings;
    private String notes;
    private List<String> photos;
    private List<String> tags;
    private int rating;
    private String recipeSource;
    private String categoryName;

    @OneToOne
    @JoinColumn(name = "nutrition_Details_Id")
    private NutritionDetails nutritionDetails;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @OneToOne
    @JoinColumn(name = "shopping_List_Id")
    private ShoppingList shoppingList;

    public Long getRecipeId() {
        return recipeId;
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

    public NutritionDetails getNutritionDetails() {
        return nutritionDetails;
    }

    public void setNutritionDetails(NutritionDetails nutritionDetails) {
        this.nutritionDetails = nutritionDetails;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
}
