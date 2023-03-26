package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeDto rdto, BindingResult br){

        ResponseEntity<Object> sb = getObjectResponseEntity(br);
        if (sb != null) return sb;

        Long recipeId = recipeService.createRecipe(rdto);
        rdto.recipeId = recipeId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + recipeId).toUriString());

        return ResponseEntity.created(uri).body(rdto);

    }

    static ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
        if (br.hasFieldErrors()){
            StringBuilder sb =new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }

            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
