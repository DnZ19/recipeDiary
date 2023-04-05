package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.*;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.NutritionDetailsService;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final NutritionDetailsService nutritionDetailsService;


    public RecipeController(RecipeService recipeService, NutritionDetailsService nutritionDetailsService) {
        this.recipeService = recipeService;
        this.nutritionDetailsService = nutritionDetailsService;
    }

    @PostMapping
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeDto rdto, BindingResult br){

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long recipeId = recipeService.createRecipe(rdto);
        rdto.recipeId = recipeId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + recipeId).toUriString());

        return ResponseEntity.created(uri).body(rdto);

    }

    @PutMapping("{recipeId}/{nutritionDetailsId}")
    public ResponseEntity<RecipeDto> linkNutritionToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("nutritionDetailsId" ) Long nutritionDetailsId){

        RecipeDto rdto = recipeService.linkNutritionToRecipe(recipeId, nutritionDetailsId);

        return ResponseEntity.ok(rdto);

    }

    @PutMapping("{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<RecipeDto> linkIngredientsToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingredientId){

        RecipeDto rdto = recipeService.linkIngredientsToRecipe(recipeId, ingredientId);

        return ResponseEntity.ok(rdto);

    }


    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long recipeId){

        RecipeDto rdto = recipeService.getRecipe(recipeId);

        return ResponseEntity.ok(rdto);
    }

    @GetMapping("/{recipeId}/nutritionDetails")
    public ResponseEntity<NutritionDetailsDto> getRecipeNutrition(@PathVariable Long recipeId){
        NutritionDetailsDto nutritionDetailsDto = recipeService.getRecipeNutrition(recipeId);

        return ResponseEntity.ok(nutritionDetailsDto);

    }

    @GetMapping("/{recipeId}/ingredients")
    public ResponseEntity<List<IngredientDto>> getRecipeIngredients(@PathVariable Long recipeId) {
        List<IngredientDto> ingredientDtos = recipeService.getRecipeIngredients(recipeId);
        return ResponseEntity.ok(ingredientDtos);
    }


    @GetMapping("/{recipeId}/shopping_list")
    public ResponseEntity<ShoppingListDto> getRecipeShoppingList(@PathVariable Long recipeId) {
        ShoppingListDto shoppingListDto = recipeService.getRecipeShoppingList(recipeId);

        List<IngredientsForShoppingListDto> ingredientsForShoppingListDtos = new ArrayList<>();
        for (IngredientsForShoppingListDto ingredientsForShoppingListDto : shoppingListDto.getIngredients()) {
            IngredientsForShoppingListDto ingredientsForShoppingListDto1 = new IngredientsForShoppingListDto();
            ingredientsForShoppingListDto1.setIngredientId(ingredientsForShoppingListDto.getIngredientId());
            ingredientsForShoppingListDto1.setIngredientName(ingredientsForShoppingListDto.getIngredientName());
            ingredientsForShoppingListDto1.setQuantity(ingredientsForShoppingListDto.getQuantity());
            ingredientsForShoppingListDto1.setUnit(ingredientsForShoppingListDto.getUnit());
            ingredientsForShoppingListDtos.add(ingredientsForShoppingListDto1);
        }
        shoppingListDto.setIngredients(ingredientsForShoppingListDtos);

        return ResponseEntity.ok(shoppingListDto);

    }

}
