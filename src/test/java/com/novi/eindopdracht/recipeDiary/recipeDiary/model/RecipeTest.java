package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void shouldReturnRecipeId() {

        Recipe recipe = new Recipe();
        Long id = 1L;

        recipe.setRecipeId(id);

        assertEquals(id, recipe.getRecipeId());
    }

    @Test
    void shouldReturnRecipeName() {

        Recipe recipe = new Recipe();
        String name = "Delicious Recipe";
        recipe.setName(name);

        String result = recipe.getName();

        assertEquals("Delicious Recipe", result);
    }

    @Test
    void shouldReturnIngredientsOfRecipe() {

        Recipe recipe = new Recipe();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredients.add(new Ingredient());

        recipe.setIngredients(ingredients);

        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    void shouldReturnPrepTimeForRecipe() {

        Recipe recipe = new Recipe();
        String prepTime = "30 minutes";

        recipe.setPrepTime(prepTime);

        assertEquals(prepTime, recipe.getPrepTime());
    }

    @Test
    void shouldReturnServingsForRecipe() {

        Recipe recipe = new Recipe();
        int servings = 4;

        recipe.setServings(servings);

        assertEquals(servings, recipe.getServings());
    }

    @Test
    void shouldReturnNotesForRecipe() {

        Recipe recipe = new Recipe();
        String notes = "Some important notes.";

        recipe.setNotes(notes);

        assertEquals(notes, recipe.getNotes());
    }

    @Test
    void shouldReturnTagForRecipe() {

        Recipe recipe = new Recipe();
        String tag = "Dessert";

        recipe.setTag(tag);

        assertEquals(tag, recipe.getTag());
    }

    @Test
    void shouldReturnRatingForRecipe() {

        Recipe recipe = new Recipe();
        int rating = 5;

        recipe.setRating(rating);

        assertEquals(rating, recipe.getRating());
    }

    @Test
    void shouldReturnRecipeSourceForRecipe() {

        Recipe recipe = new Recipe();
        String source = "https://example.com";

        recipe.setRecipeSource(source);

        assertEquals(source, recipe.getRecipeSource());
    }

    @Test
    void shouldReturnCategoryNameForRecipe() {

        Recipe recipe = new Recipe();
        String categoryName = "Asian";

        recipe.setCategoryName(categoryName);

        assertEquals(categoryName, recipe.getCategoryName());
    }

    @Test
    void shouldReturnShoppingListForRecipe() {

        Recipe recipe = new Recipe();
        ShoppingList shoppingList = new ShoppingList();

        recipe.setShoppingList(shoppingList);

        assertEquals(shoppingList, recipe.getShoppingList());
    }

    @Test
    void shouldReturnPhotoForRecipe() {

        Recipe recipe = new Recipe();
        Photo photo = new Photo();

        recipe.setPhoto(photo);

        assertEquals(photo, recipe.getPhoto());
    }

    @Test
    void shouldReturnRecipeDiaryForRecipe() {

        Recipe recipe = new Recipe();
        RecipeDiary recipeDiary = new RecipeDiary();

        recipe.setRecipeDiary(recipeDiary);

        assertEquals(recipeDiary, recipe.getRecipeDiary());
    }
}