package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long photoId;
    private String dishName;

    @OneToOne(mappedBy = "photo")
    private Recipe recipe;

    @Lob
    @Column(nullable = true, length = 64)
    @JsonIgnore
    private byte[] imageData;

    public Long getPhotoId() {
        return photoId;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
