package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_diaries")
public class RecipeDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long recipeDiaryId;
    private String name;

    public RecipeDiary() {
    }

    public RecipeDiary(Long recipeDiaryId, String name) {
        this.recipeDiaryId = recipeDiaryId;
        this.name = name;
    }

    public Long getRecipeDiaryId() {
        return recipeDiaryId;
    }

    public void setRecipeDiaryId(Long recipeDiaryId) {
        this.recipeDiaryId = recipeDiaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
