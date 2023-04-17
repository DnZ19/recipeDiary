package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository rRepos;

    @InjectMocks
    RecipeService rService;

    @Test
    void shouldReturnACreatedRecipe() {
        // Arrange
        RecipeDto rdto = new RecipeDto();
        Recipe recipe = new Recipe();
        Long recipeId = 1L;
        recipe.setRecipeId(recipeId);
        when(rRepos.save(any(Recipe.class))).thenReturn(recipe);

        // Act
        Long id = rService.createRecipe(rdto);

        // Assert
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(rRepos, times(1)).save(recipeCaptor.capture());
        Recipe savedRecipe = recipeCaptor.getValue();

        assertNotNull(savedRecipe);
        assertEquals(recipeId, id);
    }

    @Test
    void shouldReturnTheRightRecipe() {
        // Arrange
        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        when(rRepos.findById(recipeId)).thenReturn(Optional.of(recipe));

        // Act
        RecipeDto result = rService.getRecipe(recipeId);

        // Assert
        assertEquals(recipeId, result.recipeId);
        verify(rRepos, times(1)).findById(recipeId);
    }

    @Test
    void shouldUpdateNotesInRecipe() {
        // Arrange
        Long recipeId = 1L;
        String newNotes = "New notes";
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setNotes("");
        when(rRepos.findById(recipeId)).thenReturn(Optional.of(recipe));

        // Act
        rService.updateNotes(recipeId, newNotes);

        // Assert
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(rRepos, times(1)).save(recipeCaptor.capture());
        Recipe updatedRecipe = recipeCaptor.getValue();

        assertEquals(newNotes, updatedRecipe.getNotes());
        verify(rRepos, times(1)).findById(recipeId);
    }

    @Test
    void shouldReturnTheRightSearchResultForRecipeByIngredient() {
        // Arrange
        String ingredientName = "Sugar";

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);

        Recipe recipe = new Recipe();
        recipe.setIngredients(List.of(ingredient));

        List<Recipe> allRecipes = List.of(recipe);
        when(rRepos.findAll()).thenReturn(allRecipes);

        // Act
        List<RecipeDto> result = rService.searchRecipeByIngredient(ingredientName);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(rRepos, times(1)).findAll();
    }

}
