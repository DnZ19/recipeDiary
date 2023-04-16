package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

public class PhotoDto {

    public Long photoId;
    public String dishName;
    @JsonIgnore
    public Recipe recipe;
    public String imageData;
    @JsonIgnore
    public String errorMessage;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getDishName() {
        return dishName;
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

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
