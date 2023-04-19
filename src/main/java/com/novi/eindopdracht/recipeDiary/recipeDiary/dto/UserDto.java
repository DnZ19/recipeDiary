package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;

public class UserDto {

    public Long userId;
    public String username;
    public String password;
    public String[] roles;
    public RecipeDiary recipeDiary;

    public RecipeDiary getRecipeDiary() {
        return recipeDiary;
    }

    public void setRecipeDiary(RecipeDiary recipeDiary) {
        this.recipeDiary = recipeDiary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
