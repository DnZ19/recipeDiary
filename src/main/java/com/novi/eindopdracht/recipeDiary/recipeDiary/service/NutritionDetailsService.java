package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.NutritionDetailsRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class NutritionDetailsService {

    private final NutritionDetailsRepository nutritionRepos;

    public NutritionDetailsService(NutritionDetailsRepository nutritionRepos) {
        this.nutritionRepos = nutritionRepos;
    }

    public Long createNutritionDetails(NutritionDetailsDto nddto){

        NutritionDetails nd = new NutritionDetails();
        nd.setCalories(nddto.calories);
        nd.setFat(nddto.fat);
        nd.setProtein(nddto.protein);
        nd.setCarbs(nddto.carbs);

        nutritionRepos.save(nd);

        return nd.getNutritionDetailsId();

    }

    public NutritionDetailsDto getNutritionDetails(Long nutritionDetailsId){

        try{
            NutritionDetails nd = nutritionRepos.findById(nutritionDetailsId).orElseThrow(() -> new ResourceNotFoundException("Nutrition item not found"));

            NutritionDetailsDto nddto = new NutritionDetailsDto();
            nddto.nutritionDetailsId = nd.getNutritionDetailsId();
            nddto.calories = nd.getCalories();
            nddto.fat = nd.getFat();
            nddto.protein = nd.getProtein();
            nddto.carbs = nd.getCarbs();

            return nddto;

        } catch (ResourceNotFoundException exception) {

            System.err.println(exception.getMessage());

            NutritionDetailsDto errorResponse = new NutritionDetailsDto();
            errorResponse.errorMessage = exception.getMessage();
            return errorResponse;
        }

    }



}
