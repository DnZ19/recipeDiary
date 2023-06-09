package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.NutritionDetailsRepository;
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

            NutritionDetails nd = nutritionRepos.findById(nutritionDetailsId)
                    .orElseThrow(() -> new ResourceNotFoundException("Nutrition item not found with id: " + nutritionDetailsId));

            NutritionDetailsDto nddto = new NutritionDetailsDto();
            nddto.nutritionDetailsId = nd.getNutritionDetailsId();
            nddto.calories = nd.getCalories();
            nddto.fat = nd.getFat();
            nddto.protein = nd.getProtein();
            nddto.carbs = nd.getCarbs();

            return nddto;

        }

    }

