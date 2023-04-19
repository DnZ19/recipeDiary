package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;


@Service
public class IngredientService {

    private final IngredientRepository iRepos;
    private final RecipeRepository rRepos;

    public IngredientService(IngredientRepository iRepos, RecipeRepository rRepos) {
        this.iRepos = iRepos;
        this.rRepos = rRepos;
    }

    public Long createIngredient(IngredientDto idto){

        Ingredient i = new Ingredient();
        i.setIngredientName(idto.ingredientName);
        i.setQuantity(idto.quantity);
        i.setUnit(idto.unit);
        i.setCategoryName(idto.categoryName);
        i.setErrorMessage(idto.errorMessage);

        if (idto.recipeId != null) {
            Recipe recipe = rRepos.findById(idto.recipeId).get();
            i.setRecipe(recipe);
        }

        iRepos.save(i);

        return i.getIngredientId();
    }

    public IngredientDto getIngredient(Long ingredientId){

            Ingredient i = iRepos.findById(ingredientId)
                    .orElseThrow(()-> new ResourceNotFoundException("Ingredient not found with id: " + ingredientId));

            IngredientDto idto = new IngredientDto();
            idto.ingredientId = i.getIngredientId();
            idto.ingredientName = i.getIngredientName();
            idto.quantity = i.getQuantity();
            idto.unit = i.getUnit();
            idto.categoryName = i.getCategoryName();
            idto.errorMessage = i.getErrorMessage();

            idto.recipeId = i.getRecipe().getRecipeId();

            return idto;

        }

    }

