package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.User;

public class RecipeDiaryDto {

    public Long recipeDiaryId;
    public String name;
    @JsonIgnore
    public User user;
    @JsonIgnore
    public String errorMessage;

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
}
