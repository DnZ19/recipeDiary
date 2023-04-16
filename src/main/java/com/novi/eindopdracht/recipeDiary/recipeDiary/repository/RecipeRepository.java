package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository <Recipe, Long> {

//    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.photo p LEFT JOIN FETCH r.nutritionDetails WHERE r.recipeDiary.recipeDiaryId = :recipeDiaryId")
//    List<Recipe> findAllRecipesByRecipeDiaryIdWithoutImageData(@Param("recipeDiaryId") Long recipeDiaryId);
}
