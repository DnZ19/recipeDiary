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
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository rRepos;
    private final NutritionDetailsRepository nutritionDetailsRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListRepository shoppingListRepository;


    public RecipeService(RecipeRepository rRepos, NutritionDetailsRepository nutritionDetailsRepository, IngredientRepository ingredientRepository, ShoppingListRepository shoppingListRepository) {
        this.rRepos = rRepos;
        this.nutritionDetailsRepository = nutritionDetailsRepository;
        this.ingredientRepository = ingredientRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public Long createRecipe(RecipeDto rdto) {

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

        rRepos.save(r);

        return r.getRecipeId();
    }

    public RecipeDto getRecipe(Long recipeId) {
        try {
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

            return rdto;

        } catch (ResourceNotFoundException exception) {
            System.err.println(exception.getMessage());

            RecipeDto errorResponse = new RecipeDto();
            errorResponse.errorMessage = exception.getMessage();
            return errorResponse;
        }


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

    public ShoppingListDto getRecipeShoppingList(Long recipeId) {

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        ShoppingList shoppingList = recipe.getShoppingList();

        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setShoppingListId(shoppingList.getShoppingListId());
        shoppingListDto.setShoppingListName(shoppingList.getShoppingListName());

        List<IngredientsForShoppingListDto> ingredientsForShoppingListDtos = new ArrayList<>();
        for (Ingredient i : shoppingList.getIngredients()) {
            IngredientsForShoppingListDto ingredientsForShoppingListDto = new IngredientsForShoppingListDto();
            ingredientsForShoppingListDto.setIngredientName(i.getIngredientName());
            ingredientsForShoppingListDto.setQuantity(i.getQuantity());
            ingredientsForShoppingListDto.setUnit(i.getUnit());
            ingredientsForShoppingListDtos.add(ingredientsForShoppingListDto);
        }

        shoppingListDto.setIngredients(ingredientsForShoppingListDtos);

        return shoppingListDto;

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

    private IngredientsForShoppingListDto ingredientToIngredientsForShoppingListDto(Ingredient ingredient) {
        IngredientsForShoppingListDto ingredientsForShoppingListDto = new IngredientsForShoppingListDto();
        ingredientsForShoppingListDto.setIngredientId(ingredient.getIngredientId());
        ingredientsForShoppingListDto.setIngredientName(ingredient.getIngredientName());
        ingredientsForShoppingListDto.setQuantity(ingredient.getQuantity());
        ingredientsForShoppingListDto.setUnit(ingredient.getUnit());
        return ingredientsForShoppingListDto;
    }

}














