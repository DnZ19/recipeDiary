package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeDiaryTest {

    @Test
    void shouldReturnRecipeDiaryId() {
        // Arrange
        RecipeDiary recipeDiary = new RecipeDiary();
        Long id = 1L;

        // Act
        recipeDiary.setRecipeDiaryId(id);

        // Assert
        assertEquals(id, recipeDiary.getRecipeDiaryId());
    }

    @Test
    void shouldReturnNameOfRecipeDiary() {
        // Arrange
        RecipeDiary recipeDiary = new RecipeDiary();
        String name = "My Recipe Diary";

        // Act
        recipeDiary.setName(name);

        // Assert
        assertEquals(name, recipeDiary.getName());
    }

    @Test
    void shouldReturnUser() {
        // Arrange
        RecipeDiary recipeDiary = new RecipeDiary();
        User user = new User();

        // Act
        recipeDiary.setUser(user);

        // Assert
        assertEquals(user, recipeDiary.getUser());
    }

    @Test
    void shouldReturnRecipes() {
        // Arrange
        RecipeDiary recipeDiary = new RecipeDiary();
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        // Act
        recipeDiary.setRecipes(recipes);

        // Assert
        assertEquals(recipes, recipeDiary.getRecipes());
    }
}
