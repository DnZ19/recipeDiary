package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository slRepos;
    private final RecipeRepository rRepos;
    private final IngredientRepository iRepos;

    public ShoppingListService(ShoppingListRepository slRepos, RecipeRepository rRepos, IngredientRepository iRepos) {
        this.slRepos = slRepos;
        this.rRepos = rRepos;
        this.iRepos = iRepos;
    }


    public Long createShoppingList(ShoppingListDto slDto){

        ShoppingList sl = new ShoppingList();
        sl.setShoppingListName(slDto.shoppingListName);

        Long recipeId = slDto.getRecipeId() != null ? slDto.getRecipeId() : 1L;
        Recipe recipe = rRepos.findById(recipeId).get();
        sl.setRecipe(recipe);

        Long ingredientId = slDto.getIngredientId() != null ? slDto.getIngredientId() : 1L;
        Ingredient ingredient = iRepos.findById(ingredientId).get();

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);
        sl.setIngredients(ingredientList);

        slRepos.save(sl);

        return sl.getShoppingListId();

    }

    public ShoppingListDto getShoppingList(Long shoppingListId){

        ShoppingList s = slRepos.findById(shoppingListId).orElseThrow(()-> new ResourceNotFoundException("ShoppingList not found"));

        ShoppingListDto slDto = new ShoppingListDto();
        slDto.setShoppingListId(s.getShoppingListId());
        slDto.setShoppingListName(s.getShoppingListName());

        return slDto;


    }


}








