package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionDetailsRepository extends CrudRepository <NutritionDetails, Long> {



}
