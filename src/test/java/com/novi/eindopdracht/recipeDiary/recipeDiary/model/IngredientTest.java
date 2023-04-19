package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void shouldReturnIngredientId() {

        Ingredient ingredient = new Ingredient();
        Long id = 1L;

        ingredient.setIngredientId(id);

        assertEquals(id, ingredient.getIngredientId());
    }

    @Test
    void shouldReturnIngredientName() {

        Ingredient ingredient = new Ingredient();
        String name = "Sugar";

        ingredient.setIngredientName(name);

        assertEquals(name, ingredient.getIngredientName());
    }

    @Test
    void shouldReturnQuantity() {

        Ingredient ingredient = new Ingredient();
        float quantity = 100.0f;

        ingredient.setQuantity(quantity);

        assertEquals(quantity, ingredient.getQuantity());
    }

    @Test
    void shouldReturnUnit() {

        Ingredient ingredient = new Ingredient();
        String unit = "grams";

        ingredient.setUnit(unit);

        assertEquals(unit, ingredient.getUnit());
    }

    @Test
    void shouldReturnCategoryName() {

        Ingredient ingredient = new Ingredient();
        String categoryName = "Baking";

        ingredient.setCategoryName(categoryName);

        assertEquals(categoryName, ingredient.getCategoryName());
    }

    @Test
    void shouldReturnRecipe() {

        Ingredient ingredient = new Ingredient();
        Recipe recipe = new Recipe();

        ingredient.setRecipe(recipe);

        assertEquals(recipe, ingredient.getRecipe());
    }

    @Test
    void shouldReturnShoppingList() {

        Ingredient ingredient = new Ingredient();
        List<ShoppingList> shoppingList = new ArrayList<>();
        shoppingList.add(new ShoppingList());
        shoppingList.add(new ShoppingList());

        ingredient.setShoppingList(shoppingList);

        assertEquals(shoppingList, ingredient.getShoppingList());
    }
}
