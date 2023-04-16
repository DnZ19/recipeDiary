package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryOverviewDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeDiaryRecipesService;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeDiaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recipeDiary")
public class RecipeDiaryController {

    private final RecipeDiaryService rdService;
    private final RecipeDiaryRecipesService recipeDiaryRecipesService;

    public RecipeDiaryController(RecipeDiaryService rdService, RecipeDiaryRecipesService recipeDiaryRecipesService){
        this.rdService = rdService;
        this.recipeDiaryRecipesService = recipeDiaryRecipesService;
    }

    @PostMapping
    public ResponseEntity<Object> createRecipeDiary(@Valid @RequestBody RecipeDiaryDto rddto, BindingResult br){

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long recipeDiaryId = rdService.createRecipeDiary(rddto);
        rddto.recipeDiaryId = recipeDiaryId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + recipeDiaryId).toUriString());

        return ResponseEntity.created(uri).body(rddto);

    }

    @PutMapping("/{recipeDiaryId}/recipes/{recipeId}")
    public ResponseEntity<RecipeDiaryDto> linkAllRecipesToDiary(@PathVariable("recipeDiaryId") Long recipeDiaryId, @PathVariable("recipeId") Long recipeId) {

        RecipeDiaryDto rddto = recipeDiaryRecipesService.linkAllRecipesToDiary(recipeDiaryId, recipeId);

        return ResponseEntity.ok(rddto);

    }


    @GetMapping("/")
    public String helloWorld(){
        return "Hello World new Diary app";
    }

    @GetMapping("/{recipeDiaryId}")
    public ResponseEntity<RecipeDiaryDto> getRecipeDiary(@PathVariable Long recipeDiaryId){
        RecipeDiaryDto rddto = rdService.getRecipeDiary(recipeDiaryId);

        return ResponseEntity.ok(rddto);
    }

    @GetMapping("/allDiaries")
    public List<RecipeDiary> getAllDiaries() {
        return rdService.getAllDiaries();
    }

    @GetMapping("/{recipeDiaryId}/recipes")
    public List<RecipeDiaryOverviewDto> getAllRecipesForDiary(@PathVariable Long recipeDiaryId) {
        return recipeDiaryRecipesService.getAllRecipesForDiary(recipeDiaryId);
    }

}
