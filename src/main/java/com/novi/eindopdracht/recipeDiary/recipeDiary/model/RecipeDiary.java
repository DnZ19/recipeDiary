package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_diaries")
public class RecipeDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long recipeDiaryId;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public RecipeDiary() {
    }

    public Long getRecipeDiaryId() {
        return recipeDiaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipeDiaryId(Long recipeDiaryId) {
        this.recipeDiaryId = recipeDiaryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
