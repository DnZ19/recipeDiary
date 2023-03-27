package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDiaryRepository extends CrudRepository <RecipeDiary, Long> {

}


