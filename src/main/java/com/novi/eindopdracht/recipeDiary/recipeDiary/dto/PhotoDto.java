package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;

public class PhotoDto {

    public Long photoId;
    public String dishName;
    @JsonIgnore
    public Recipe recipe;
    public String imageData;
    @JsonIgnore
    public String errorMessage;

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
