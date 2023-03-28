package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.ShoppingListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("shopping_list")
public class ShoppingListController {

    private final ShoppingListService slService;

    public ShoppingListController(ShoppingListService slService){
        this.slService = slService;
    }

    @PostMapping
    public ResponseEntity<Object> createShoppingList(@Valid @RequestBody ShoppingListDto slDto, BindingResult br){

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long shoppingListId = slService.createShoppingList(slDto);
        slDto.shoppingListId = shoppingListId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + shoppingListId).toUriString());

        return ResponseEntity.created(uri).body(slDto);

    }

    @GetMapping("/{shoppingListId}")
    public  ResponseEntity<ShoppingListDto> getShoppingList(@PathVariable Long shoppingListId){
        ShoppingListDto slDto = slService.getShoppingList(shoppingListId);

        return ResponseEntity.ok(slDto);
    }


}
