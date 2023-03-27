package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository <Recipe, Long> {


}
