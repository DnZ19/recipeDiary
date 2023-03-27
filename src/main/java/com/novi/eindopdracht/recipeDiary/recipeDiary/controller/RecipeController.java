package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.NutritionDetailsService;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;

        Long recipeId = recipeService.createRecipe(rdto);
        rdto.recipeId = recipeId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + recipeId).toUriString());

        return ResponseEntity.created(uri).body(rdto);

    }

    static ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
        ResponseEntity<Object> sb = NutritionDetailsController.getObjectResponseEntity(br);
        if (sb != null) return sb;
        return null;
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






}
