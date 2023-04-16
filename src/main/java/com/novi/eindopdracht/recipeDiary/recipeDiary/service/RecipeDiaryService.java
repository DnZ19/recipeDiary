package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeDiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeDiaryService {

    private final RecipeDiaryRepository diaryRepos;

    public RecipeDiaryService(RecipeDiaryRepository diaryRepos) {
        this.diaryRepos = diaryRepos;
    }

    public Long createRecipeDiary(RecipeDiaryDto rddto){

        RecipeDiary rd = new RecipeDiary();
        rd.setName(rddto.name);
        rd.setRecipes(rddto.getRecipes());

        diaryRepos.save(rd);

        return rd.getRecipeDiaryId();

    }

    public RecipeDiaryDto getRecipeDiary(Long recipeDiaryId) {
        RecipeDiary rd = diaryRepos.findById(recipeDiaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe Diary not found with ID: " + recipeDiaryId));

        RecipeDiaryDto rddto = new RecipeDiaryDto();
        rddto.recipeDiaryId = rd.getRecipeDiaryId();
        rddto.name = rd.getName();
        rddto.recipes = rd.getRecipes();

        return rddto;
    }


    public List<RecipeDiary> getAllDiaries()
    {
        return (List<RecipeDiary>) diaryRepos.findAll();
    }

}
