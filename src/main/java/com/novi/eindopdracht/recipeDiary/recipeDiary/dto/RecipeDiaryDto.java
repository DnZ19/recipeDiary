package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.User;

import java.util.List;

public class RecipeDiaryDto {

    public Long recipeDiaryId;
    public String name;
    //@JsonIgnore
    public User user;
    @JsonIgnore
    public String errorMessage;
    @JsonIgnore
    public List<Recipe> recipes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRecipeDiaryId() {
        return recipeDiaryId;
    }

    public void setRecipeDiaryId(Long recipeDiaryId) {
        this.recipeDiaryId = recipeDiaryId;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
