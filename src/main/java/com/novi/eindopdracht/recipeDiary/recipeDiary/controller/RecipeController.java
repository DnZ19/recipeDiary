package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
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
import java.util.List;

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
    public ResponseEntity<ShoppingListDto> getRecipeShoppingList(@PathVariable Long recipeId){
        ShoppingListDto shoppingListDto = recipeService.getRecipeShoppingList(recipeId);
        return ResponseEntity.ok(shoppingListDto);
    }



    // les materiaal...........


//    @GetMapping("/hello")
//    public String sayHello(){
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getPrincipal() instanceof UserDetails){
//            UserDetails ud = (UserDetails) auth.getPrincipal();
//
//            return "Hello " + ud.getUsername();
//        }
//
//        return "Hello stranger";
//
//    }



}
