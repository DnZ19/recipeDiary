package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository rRepos;

    public RecipeService(RecipeRepository rRepos) {
        this.rRepos = rRepos;
    }

    public Long createRecipe(RecipeDto rdto) {

        Recipe r = new Recipe();
        r.setName(rdto.name);
        r.setInstructions(rdto.instructions);
        r.setPrepTime(rdto.prepTime);
        r.setServings(rdto.servings);
        r.setNotes(rdto.notes);
        r.setTag(rdto.tag);
        r.setRating(rdto.rating);
        r.setRecipeSource(rdto.recipeSource);
        r.setCategoryName(rdto.categoryName);

        Recipe savedRecipe = rRepos.save(r);

        return savedRecipe.getRecipeId();
    }

    public RecipeDto getRecipe(Long recipeId) {
        Recipe r = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
        return convertRecipeToRecipeDto(r);
    }

    public void updateNotes(Long recipeId, String newNotes) {
        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
        String currentNotes = recipe.getNotes();

        if (!currentNotes.isEmpty()) {
            recipe.setNotes(currentNotes + "\n" + newNotes);
        } else {
            recipe.setNotes(newNotes);
        }

        rRepos.save(recipe);
    }

    private RecipeDto convertRecipeToRecipeDto(Recipe recipe) {
        RecipeDto rdto = new RecipeDto();
        rdto.recipeId = recipe.getRecipeId();
        rdto.name = recipe.getName();
        rdto.instructions = recipe.getInstructions();
        rdto.prepTime = recipe.getPrepTime();
        rdto.servings = recipe.getServings();
        rdto.notes = recipe.getNotes();
        rdto.tag = recipe.getTag();
        rdto.rating = recipe.getRating();
        rdto.recipeSource = recipe.getRecipeSource();
        rdto.categoryName = recipe.getCategoryName();
        rdto.ingredients = recipe.getIngredients();
        rdto.nutritionDetails = recipe.getNutritionDetails();
        rdto.recipeDiary = recipe.getRecipeDiary();

        return rdto;
    }

    public List<RecipeDto> searchRecipeByIngredient(String ingredientName) {
        List<Recipe> allRecipes = rRepos.findAll();

        List<Recipe> filteredRecipes = allRecipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .anyMatch(ingredient -> ingredient.getIngredientName().equalsIgnoreCase(ingredientName)))
                .toList();

        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe recipe : filteredRecipes) {
            recipeDtos.add(convertRecipeToRecipeDto(recipe));
        }

        return recipeDtos;
    }

    }
