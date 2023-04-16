package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController extends BaseController {

    private final RecipeService recipeService;
    private final RecipeNutritionService nutritionService;
    private final RecipeIngredientsService ingredientsService;
    private final RecipeShoppingListService shoppingListService;
    private final RecipePhotoService photoService;


    public RecipeController(RecipeService recipeService, RecipeNutritionService nutritionService, RecipeIngredientsService ingredientsService, RecipeShoppingListService shoppingListService, RecipePhotoService photoService) {
        this.recipeService = recipeService;
        this.nutritionService = nutritionService;
        this.ingredientsService = ingredientsService;
        this.shoppingListService = shoppingListService;
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeDto rdto, BindingResult bindingResult){

        ResponseEntity<Object> errorResponse = buildErrorResponse(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        Long recipeId = recipeService.createRecipe(rdto);
        rdto.recipeId = recipeId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + recipeId).toUriString());

        return ResponseEntity.created(uri).body(rdto);

    }

    @PutMapping("{recipeId}/{nutritionDetailsId}")
    public ResponseEntity<RecipeDto> linkNutritionToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("nutritionDetailsId" ) Long nutritionDetailsId){

        RecipeDto rdto = nutritionService.linkNutritionToRecipe(recipeId, nutritionDetailsId);

        return ResponseEntity.ok(rdto);

    }

    @PutMapping("{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<RecipeDto> linkIngredientsToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingredientId){

        RecipeDto rdto = ingredientsService.linkIngredientsToRecipe(recipeId, ingredientId);

        return ResponseEntity.ok(rdto);

    }

    @PutMapping("{recipeId}/shopping_list/{shoppingListId}")
    public ResponseEntity<RecipeDto> linkRecipeToShoppingList(@PathVariable("recipeId") Long recipeId, @PathVariable("shoppingListId") Long shoppingListId){

        RecipeDto rdto = shoppingListService.linkRecipeToShoppingList(recipeId, shoppingListId);

        return ResponseEntity.ok(rdto);

    }

    @PutMapping("{recipeId}/photo/{photoId}")
    public ResponseEntity<RecipeDto> linkPhotoToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("photoId") Long photoId){

        RecipeDto rdto = photoService.linkPhotoToRecipe(recipeId, photoId);

        return ResponseEntity.ok(rdto);

    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long recipeId){

        RecipeDto rdto = recipeService.getRecipe(recipeId);

        return ResponseEntity.ok(rdto);
    }

    @GetMapping("/{recipeId}/nutritionDetails")
    public ResponseEntity<NutritionDetailsDto> getRecipeNutrition(@PathVariable Long recipeId){
        NutritionDetailsDto nutritionDetailsDto = nutritionService.getRecipeNutrition(recipeId);

        return ResponseEntity.ok(nutritionDetailsDto);

    }

    @GetMapping("/{recipeId}/ingredients")
    public ResponseEntity<List<IngredientDto>> getRecipeIngredients(@PathVariable Long recipeId) {
        List<IngredientDto> ingredientDtos = ingredientsService.getRecipeIngredients(recipeId);
        return ResponseEntity.ok(ingredientDtos);
    }

    @GetMapping("/{recipeId}/shopping_list")
    public ResponseEntity<ShoppingListDto> getRecipeShoppingList(@PathVariable Long recipeId) {
        ShoppingListDto shoppingListDto = shoppingListService.getRecipeShoppingList(recipeId);

        return ResponseEntity.ok(shoppingListDto);

    }

    @GetMapping("/{recipeId}/photo")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable Long recipeId) {
        PhotoDto photoDto = photoService.getPhoto(recipeId);

        return ResponseEntity.ok(photoDto);
    }

}
