package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.NutritionDetailsRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        rRepos.save(r);

        return r.getRecipeId();
    }

    public RecipeDto getRecipe(Long recipeId) {

            Recipe r = rRepos.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));

            RecipeDto rdto = new RecipeDto();
            rdto.recipeId = r.getRecipeId();
            rdto.name = r.getName();
            rdto.instructions = r.getInstructions();
            rdto.prepTime = r.getPrepTime();
            rdto.servings = r.getServings();
            rdto.notes = r.getNotes();
            rdto.tag = r.getTag();
            rdto.rating = r.getRating();
            rdto.recipeSource = r.getRecipeSource();
            rdto.categoryName = r.getCategoryName();

            if (r.getIngredients() != null) {
                rdto.ingredients = r.getIngredients();
            }
            if (r.getNutritionDetails() != null) {
                rdto.nutritionDetails = r.getNutritionDetails();
            }
            if (r.getRecipeDiary() != null) {
                rdto.recipeDiary = r.getRecipeDiary();
            }

            return rdto;

        }

    }
