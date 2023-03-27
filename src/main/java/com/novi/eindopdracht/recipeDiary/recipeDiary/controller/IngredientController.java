package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.IngredientDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("ingredients")
public class IngredientController {

    private final IngredientService iService;


    public IngredientController(IngredientService iService) {
        this.iService = iService;
    }

    @PostMapping
    public ResponseEntity<Object> createIngredient(@Valid @RequestBody IngredientDto idto, BindingResult br){

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long ingredientId = iService.createIngredient(idto);
        idto.ingredientId = ingredientId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + ingredientId).toUriString());

        return ResponseEntity.created(uri).body(idto);

    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<IngredientDto> getIngredient(@PathVariable Long ingredientId){
        IngredientDto idto = iService.getIngredient(ingredientId);

        return ResponseEntity.ok(idto);
    }

}
