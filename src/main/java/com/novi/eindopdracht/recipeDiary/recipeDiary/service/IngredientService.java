package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Ingredient;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.IngredientRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository iRepos;


    public IngredientService(IngredientRepository iRepos) {
        this.iRepos = iRepos;
    }

    public Long createIngredient(IngredientDto idto){

        Ingredient i = new Ingredient();
        i.setIngredientName(idto.ingredientName);
        i.setQuantity(idto.quantity);
        i.setUnit(idto.unit);
        i.setCategoryName(idto.categoryName);
        i.setErrorMessage(idto.errorMessage);

        iRepos.save(i);

        return i.getIngredientId();
    }

    public IngredientDto getIngredient(Long ingredientId){

        String errorMessage;

        try{

            Ingredient i = iRepos.findById(ingredientId).orElseThrow(()-> new ResourceNotFoundException("Ingredient not found"));

            IngredientDto idto = new IngredientDto();
            idto.ingredientId = i.getIngredientId();
            idto.ingredientName = i.getIngredientName();
            idto.quantity = i.getQuantity();
            idto.unit = i.getUnit();
            idto.categoryName = i.getCategoryName();
            idto.errorMessage = i.getErrorMessage();

            return idto;

        } catch (ResourceNotFoundException exception) {

            System.err.println(exception.getMessage());

            IngredientDto errorResponse = new IngredientDto();
            errorResponse.errorMessage = exception.getMessage();



            return errorResponse;
        }

    }


}
