package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.NutritionDetailsRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeNutritionService {

    private final RecipeRepository rRepos;
    private final NutritionDetailsRepository nutritionDetailsRepository;

    public RecipeNutritionService(RecipeRepository rRepos, NutritionDetailsRepository nutritionDetailsRepository) {
        this.rRepos = rRepos;
        this.nutritionDetailsRepository = nutritionDetailsRepository;
    }

    public NutritionDetailsDto getRecipeNutrition(Long recipeId) {

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        NutritionDetails nutritionDetails = recipe.getNutritionDetails();

        NutritionDetailsDto nutritionDetailsDto = new NutritionDetailsDto();
        nutritionDetailsDto.setNutritionDetailsId(nutritionDetails.getNutritionDetailsId());
        nutritionDetailsDto.setFat(nutritionDetails.getFat());
        nutritionDetailsDto.setCarbs(nutritionDetails.getCarbs());
        nutritionDetailsDto.setProtein(nutritionDetails.getProtein());
        nutritionDetailsDto.setCalories(nutritionDetails.getCalories());

        return nutritionDetailsDto;
    }

    public RecipeDto linkNutritionToRecipe(Long recipeId, Long nutritionDetailsId) {

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        NutritionDetails nutritionDetails = nutritionDetailsRepository.findById(nutritionDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("Nutrition", "id", nutritionDetailsId));

        recipe.setNutritionDetails(nutritionDetails);

        Recipe recipe1 = rRepos.save(recipe);

        return recipeModelToDto(recipe1);

    }

    public RecipeDto recipeModelToDto(Recipe recipe){

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setRecipeId(recipe.getRecipeId());
        recipeDto.setName(recipe.getName());
        recipeDto.setInstructions(recipe.getInstructions());
        recipeDto.setNutritionDetails(recipe.getNutritionDetails());
        recipeDto.setPrepTime(recipe.getPrepTime());
        recipeDto.setServings(recipe.getServings());
        recipeDto.setNotes(recipe.getNotes());
        recipeDto.setRating(recipe.getRating());
        recipeDto.setRecipeSource(recipe.getRecipeSource());
        recipeDto.setCategoryName(recipe.getCategoryName());

        return recipeDto;

    }


}
