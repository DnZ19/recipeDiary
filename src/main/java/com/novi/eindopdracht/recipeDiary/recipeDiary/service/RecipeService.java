package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.NutritionDetailsRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository rRepos;
    private final NutritionDetailsRepository nutritionDetailsRepository;
    private final IngredientRepository ingredientRepository;


    public RecipeService(RecipeRepository rRepos, NutritionDetailsRepository nutritionDetailsRepository, IngredientRepository ingredientRepository) {
        this.rRepos = rRepos;
        this.nutritionDetailsRepository = nutritionDetailsRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Long createRecipe(RecipeDto rdto){

        Recipe r = new Recipe();
        r.setName(rdto.name);
        r.setInstructions(rdto.instructions);
        r.setPrepTime(rdto.prepTime);
        r.setServings(rdto.servings);
        r.setNotes(rdto.notes);
        r.setPhotos(rdto.photos);
        r.setTags(rdto.tags);
        r.setRating(rdto.rating);
        r.setRecipeSource(rdto.recipeSource);
        r.setCategoryName(rdto.categoryName);

        NutritionDetails nutritionDetails = nutritionDetailsRepository.findById(rdto.nutritionDetailsId).get();
        r.setNutritionDetails(nutritionDetails);

        rRepos.save(r);

        return r.getRecipeId();
    }

    public RecipeDto getRecipe(Long recipeId){
        try{
            Recipe r = rRepos.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));

            RecipeDto rdto = new RecipeDto();
            rdto.recipeId = r.getRecipeId();
            rdto.name = r.getName();
            rdto.instructions = r.getInstructions();
            rdto.prepTime = r.getPrepTime();
            rdto.servings = r.getServings();
            rdto.notes = r.getNotes();
            rdto.photos = r.getPhotos();
            rdto.tags = r.getTags();
            rdto.rating = r.getRating();
            rdto.recipeSource = r.getRecipeSource();
            rdto.categoryName = r.getCategoryName();

            rdto.nutritionDetailsId = r.getNutritionDetails().getNutritionDetailsId();

            return rdto;

        } catch (ResourceNotFoundException exception) {
            System.err.println(exception.getMessage());

            RecipeDto errorResponse = new RecipeDto();
            errorResponse.errorMessage = exception.getMessage();
            return errorResponse;
        }


    }

    public NutritionDetailsDto getRecipeNutrition(Long recipeId) {

        NutritionDetails nd = nutritionDetailsRepository.findById(recipeId).orElseThrow(()-> new ResourceNotFoundException("Nutrition not found"));

        NutritionDetailsDto nddto = new NutritionDetailsDto();
        nddto.nutritionDetailsId = nd.getNutritionDetailsId();
        nddto.fat = nd.getFat();
        nddto.carbs = nd.getCarbs();
        nddto.protein = nd.getProtein();
        nddto.calories = nd.getCalories();

        return nddto;
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
                ingredientDtos.add(ingredientDto);
            }

            return ingredientDtos;
        }


}
