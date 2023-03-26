package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository <Recipe, Long> {
}
