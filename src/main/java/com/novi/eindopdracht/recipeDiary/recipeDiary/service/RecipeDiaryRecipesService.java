package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryOverviewDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeDiaryRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RecipeDiaryRecipesService {

    private final RecipeRepository rRepos;
    private final RecipeDiaryRepository rdRepos;


    public RecipeDiaryRecipesService(RecipeRepository rRepos, RecipeDiaryRepository rdRepos) {
        this.rRepos = rRepos;
        this.rdRepos = rdRepos;
    }

    public List<RecipeDiaryOverviewDto> getAllRecipesForDiary(Long recipeDiaryId) {

        RecipeDiary recipeDiary = rdRepos.findById(recipeDiaryId)
                .orElseThrow(() -> new ResourceNotFoundException("RecipeDiary", "id", recipeDiaryId));

        List<Recipe> recipes = recipeDiary.getRecipes();
        if (recipes != null) {
            Hibernate.initialize(recipes);
        } else {
            recipes = new ArrayList<>();
        }

        List<RecipeDiaryOverviewDto> recipeDiaryOverviewDtos = new ArrayList<>();
        for (Recipe recipe : recipes) {
            RecipeDiaryOverviewDto recipeDiaryOverviewDto = new RecipeDiaryOverviewDto();
            recipeDiaryOverviewDto.setRecipeId(recipe.getRecipeId());
            recipeDiaryOverviewDto.setName(recipe.getName());
            recipeDiaryOverviewDto.setRating(recipe.getRating());
            recipeDiaryOverviewDto.setCategoryName(recipe.getCategoryName());

            recipeDiaryOverviewDtos.add(recipeDiaryOverviewDto);
        }
        return recipeDiaryOverviewDtos;
    }

    public RecipeDiaryDto linkAllRecipesToDiary(Long recipeDiaryId, Long recipeId) {

        RecipeDiary recipeDiary = rdRepos.findById(recipeDiaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeDiaryId));

        Recipe recipe = rRepos.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

        List<Recipe> recipes = recipeDiary.getRecipes();
        if (recipes == null){
            recipes = new ArrayList<>();
        }
        recipes.add(recipe);
        recipe.setRecipeDiary(recipeDiary);
        recipeDiary.setRecipes(recipes);

        RecipeDiary recipeDiary1 = rdRepos.save(recipeDiary);

        return RecipeToDiaryModelToDto(recipeDiary1);

    }

    public RecipeDiaryDto RecipeToDiaryModelToDto(RecipeDiary recipeDiary) {

        RecipeDiaryDto recipeDiaryDto = new RecipeDiaryDto();

        recipeDiaryDto.setRecipeDiaryId(recipeDiary.getRecipeDiaryId());
        recipeDiaryDto.setName(recipeDiary.getName());
        recipeDiaryDto.setRecipes(recipeDiary.getRecipes());
        return recipeDiaryDto;

    }

}
