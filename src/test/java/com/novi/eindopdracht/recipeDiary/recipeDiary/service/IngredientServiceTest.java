package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    IngredientRepository iRepos;

    @Mock
    RecipeRepository rRepos;

    @InjectMocks
    IngredientService iService;

    @Test
    void shouldCreateIngredient() {
        // Arrange
        IngredientDto idto = new IngredientDto();
        idto.ingredientName = "Sugar";
        idto.quantity = 1;
        idto.unit = "cup";
        idto.categoryName = "Sweets";
        idto.errorMessage = "";

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(1L);
        ingredient.setIngredientName(idto.ingredientName);
        ingredient.setQuantity(idto.quantity);
        ingredient.setUnit(idto.unit);
        ingredient.setCategoryName(idto.categoryName);
        ingredient.setErrorMessage(idto.errorMessage);

        when(iRepos.save(any(Ingredient.class))).thenAnswer(invocation -> {
            Ingredient ing = invocation.getArgument(0);
            ing.setIngredientId(1L);
            return ing;
        });

        // Act
        Long id = iService.createIngredient(idto);

        // Assert
        ArgumentCaptor<Ingredient> ingredientCaptor = ArgumentCaptor.forClass(Ingredient.class);
        verify(iRepos, times(1)).save(ingredientCaptor.capture());
        Ingredient savedIngredient = ingredientCaptor.getValue();

        System.out.println("id = " + id);
        System.out.println("savedIngredient = " + savedIngredient);

        assertNotNull(savedIngredient);
        assertEquals(ingredient.getIngredientId(), id);
    }

    @Test
    void shouldReturnTheRightIngredient() {
        // Arrange
        Long ingredientId = 1L;
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientId);
        ingredient.setIngredientName("Sugar");
        ingredient.setQuantity(1);
        ingredient.setUnit("cup");
        ingredient.setCategoryName("Sweets");
        ingredient.setErrorMessage("");

        Recipe recipe = new Recipe();
        recipe.setRecipeId(1L);
        ingredient.setRecipe(recipe);

        when(iRepos.findById(ingredientId)).thenReturn(Optional.of(ingredient));

        // Act
        IngredientDto result = iService.getIngredient(ingredientId);

        // Assert
        assertEquals(ingredientId, result.ingredientId);
        assertEquals(ingredient.getIngredientName(), result.ingredientName);
        assertEquals(ingredient.getQuantity(), result.quantity);
        assertEquals(ingredient.getUnit(), result.unit);
        assertEquals(ingredient.getCategoryName(), result.categoryName);
        assertEquals(ingredient.getErrorMessage(), result.errorMessage);
        assertEquals(ingredient.getRecipe().getRecipeId(), result.recipeId);

        verify(iRepos, times(1)).findById(ingredientId);
    }

    @Test
    void shouldCreateIngredientWithRecipe() {
        // Arrange
        IngredientDto idto = new IngredientDto();
        idto.ingredientName = "Sugar";
        idto.quantity = 1;
        idto.unit = "cup";
        idto.categoryName = "Sweets";
        idto.errorMessage = "";
        idto.recipeId = 1L;

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(2L);
        ingredient.setIngredientName(idto.ingredientName);
        ingredient.setQuantity(idto.quantity);
        ingredient.setUnit(idto.unit);
        ingredient.setCategoryName(idto.categoryName);
        ingredient.setErrorMessage(idto.errorMessage);

        Recipe recipe = new Recipe();
        recipe.setRecipeId(idto.recipeId);
        when(rRepos.findById(idto.recipeId)).thenReturn(Optional.of(recipe));

        when(iRepos.save(any(Ingredient.class))).thenAnswer(invocation -> {
            Ingredient ing = invocation.getArgument(0);
            ing.setIngredientId(2L);
            return ing;
        });


        // Act
        Long id = iService.createIngredient(idto);

        // Assert
        ArgumentCaptor<Ingredient> ingredientCaptor = ArgumentCaptor.forClass(Ingredient.class);
        verify(iRepos, times(1)).save(ingredientCaptor.capture());
        Ingredient savedIngredient = ingredientCaptor.getValue();

        assertNotNull(savedIngredient);
        assertEquals(ingredient.getIngredientId(), id);
        assertEquals(recipe, savedIngredient.getRecipe());
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenGettingNonExistentIngredient() {
        // Arrange
        Long nonExistentId = 999L;
        when(iRepos.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> iService.getIngredient(nonExistentId));
        verify(iRepos, times(1)).findById(nonExistentId);
    }

}
