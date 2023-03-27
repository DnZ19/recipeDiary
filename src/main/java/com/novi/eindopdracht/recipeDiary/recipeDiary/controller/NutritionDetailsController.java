package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.NutritionDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("nutrition_details")
public class NutritionDetailsController {

    private final NutritionDetailsService nDetService;

    public NutritionDetailsController(NutritionDetailsService nDetService){

        this.nDetService = nDetService;

    }

    @PostMapping
    public ResponseEntity<Object> createNutritionDetails(@Valid @RequestBody NutritionDetailsDto nddto, BindingResult br){

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long nutritionDetailsId = nDetService.createNutritionDetails(nddto);
        nddto.nutritionDetailsId = nutritionDetailsId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + nutritionDetailsId).toUriString());

        return ResponseEntity.created(uri).body(nutritionDetailsId);

    }

//    static ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
//        if (br.hasFieldErrors()){
//            StringBuilder sb = new StringBuilder();
//            for (FieldError fe : br.getFieldErrors()){
//                sb.append(fe.getField() + ": ");
//                sb.append(fe.getDefaultMessage() + "\n");
//            }
//            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//        return null;
//    }

    @GetMapping("/{nutritionDetailsId}")
    public ResponseEntity<NutritionDetailsDto> getNutritionDetails(@PathVariable Long nutritionDetailsId){
        NutritionDetailsDto nddto = nDetService.getNutritionDetails(nutritionDetailsId);

        return ResponseEntity.ok(nddto);
    }


}
