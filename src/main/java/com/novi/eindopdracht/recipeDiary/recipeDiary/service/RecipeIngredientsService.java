package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientsService {

    private final RecipeRepository rRepos;
    private final IngredientRepository ingredientRepository;

    public RecipeIngredientsService(RecipeRepository rRepos, IngredientRepository ingredientRepository) {
        this.rRepos = rRepos;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDto> getRecipeIngredients(Long recipeId) {

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        List<Ingredient> ingredients = recipe.getIngredients();

        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setIngredientId(ingredient.getIngredientId());
            ingredientDto.setIngredientName(ingredient.getIngredientName());
            ingredientDto.setQuantity(ingredient.getQuantity());
            ingredientDto.setUnit(ingredient.getUnit());
            ingredientDto.setCategoryName(ingredient.getCategoryName());
            ingredientDto.setRecipeId(ingredient.getRecipe().getRecipeId());
            ingredientDtos.add(ingredientDto);
        }
        return ingredientDtos;
    }

    public RecipeDto linkIngredientsToRecipe(Long recipeId, Long ingredientId) {

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(()-> new ResourceNotFoundException("Ingredients", "id", ingredientId));

        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);

        Recipe recipe1 = rRepos.save(recipe);

        return recipeIngredientsModelToDto(recipe1);

    }

    public RecipeDto recipeIngredientsModelToDto(Recipe recipe){
        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setIngredients(recipe.getIngredients());
        return recipeDto;
    }
}
