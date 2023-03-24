package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeDiaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("recipeDiaries")
public class RecipeDiaryController {

    private final RecipeDiaryService rdService;

    public RecipeDiaryController(RecipeDiaryService rdService){
        this.rdService = rdService;
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

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World new Diary app";
    }

    @GetMapping("/{recipeDiaryId}")
    public ResponseEntity<RecipeDiaryDto> getRecipeDiary(@PathVariable Long recipeDiaryId){
        RecipeDiaryDto rddto = rdService.getRecipeDiary(recipeDiaryId);

        return ResponseEntity.ok(rddto);
    }

}
