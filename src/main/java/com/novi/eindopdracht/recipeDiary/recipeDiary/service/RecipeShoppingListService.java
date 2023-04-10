package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientsForShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeShoppingListService {

    private final RecipeRepository rRepos;
    private final ShoppingListRepository shoppingListRepository;

    public RecipeShoppingListService(RecipeRepository rRepos, ShoppingListRepository shoppingListRepository) {
        this.rRepos = rRepos;
        this.shoppingListRepository = shoppingListRepository;
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
            ingredientsForShoppingListDto.setRecipeId(i.getIngredientId());
            ingredientsForShoppingListDtos.add(ingredientsForShoppingListDto);
        }

        shoppingListDto.setIngredients(ingredientsForShoppingListDtos);

        return shoppingListDto;

    }

    public RecipeDto linkRecipeToShoppingList(Long recipeId, Long shoppingListId){

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no shoppinglist."));

        recipe.setShoppingList(shoppingList);

        for (Ingredient ingredient : recipe.getIngredients()) {
            shoppingList.addIngredient(ingredient);
        }

        Recipe recipe1 = rRepos.save(recipe);

        return shoppingListModelToDto(recipe1);

    }


    public RecipeDto shoppingListModelToDto(Recipe recipe){

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setShoppingListId(recipe.getShoppingList().getShoppingListId());

        return recipeDto;
    }




}
