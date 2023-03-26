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
    private int prepTime;
    private int servings;
    private String notes;
    private List<String> photos;
    private List<String> tags;
    private int rating;
    private String recipeSource;
    private String categoryName;

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

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
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
}
