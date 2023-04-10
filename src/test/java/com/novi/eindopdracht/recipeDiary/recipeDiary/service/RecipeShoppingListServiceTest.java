//package com.novi.eindopdracht.recipeDiary.recipeDiary.service;
//
//import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientsForShoppingListDto;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.ShoppingListRepository;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class RecipeShoppingListServiceTest {
//
//    @InjectMocks
//    private ShoppingListService shoppingListService;
//
//    @Mock
//    private RecipeRepository rRepos;
//
//    @Mock
//    private ShoppingListRepository slRepos;
//
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetRecipeShoppingList_Success() {
//        Long recipeId = 1L;
//        Recipe recipe = new Recipe();
//        recipe.setRecipeId(recipeId);
//        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setShoppingListId(1L);
//        shoppingList.setShoppingListName("Test shopping list");
//        recipe.setShoppingList(shoppingList);
//
//        Ingredient ingredient = new Ingredient();
//        ingredient.setIngredientName("Test ingredient");
//        ingredient.setQuantity(2);
//        ingredient.setUnit("cups");
//        ingredient.setIngredientId(1L);
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(ingredient);
//        shoppingList.setIngredients(ingredients);
//
//        when(rRepos.findById(recipeId)).thenReturn(Optional.of(recipe));
//        when(slRepos.findById(shoppingListId)).thenReturn(Optional.of(shoppingList));
//
//
//        ShoppingListDto result = shoppingListService.getShoppingList(recipeId);
//
//        assertEquals(shoppingList.getShoppingListId(), result.getShoppingListId());
//        assertEquals(shoppingList.getShoppingListName(), result.getShoppingListName());
//        assertEquals(1, result.getIngredients().size());
//
//        IngredientsForShoppingListDto ingredientDto = result.getIngredients().get(0);
//        assertEquals(ingredient.getIngredientName(), ingredientDto.getIngredientName());
//        assertEquals(ingredient.getQuantity(), ingredientDto.getQuantity());
//        assertEquals(ingredient.getUnit(), ingredientDto.getUnit());
//        assertEquals(ingredient.getIngredientId(), ingredientDto.getRecipeId());
//    }
//
//    @Test
//    public void testGetRecipeShoppingList_RecipeNotFound() {
//        Long recipeId = 1L;
//        when(rRepos.findById(recipeId)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> shoppingListService.getShoppingList(recipeId));
//    }
//}