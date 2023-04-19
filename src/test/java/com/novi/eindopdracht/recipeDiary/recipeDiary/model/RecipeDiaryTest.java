package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeDiaryTest {

    @Test
    void shouldReturnRecipeDiaryId() {

        RecipeDiary recipeDiary = new RecipeDiary();
        Long id = 1L;

        recipeDiary.setRecipeDiaryId(id);

        assertEquals(id, recipeDiary.getRecipeDiaryId());
    }

    @Test
    void shouldReturnNameOfRecipeDiary() {

        RecipeDiary recipeDiary = new RecipeDiary();
        String name = "My Recipe Diary";

        recipeDiary.setName(name);

        assertEquals(name, recipeDiary.getName());
    }

    @Test
    void shouldReturnUser() {

        RecipeDiary recipeDiary = new RecipeDiary();
        User user = new User();

        recipeDiary.setUser(user);

        assertEquals(user, recipeDiary.getUser());
    }

    @Test
    void shouldReturnRecipes() {

        RecipeDiary recipeDiary = new RecipeDiary();
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        recipeDiary.setRecipes(recipes);

        assertEquals(recipes, recipeDiary.getRecipes());
    }
}
