package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.ShoppingListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/shopping_list")
public class ShoppingListController extends BaseController {

    private final ShoppingListService slService;

    public ShoppingListController(ShoppingListService slService){
        this.slService = slService;
    }

    @PostMapping
    public ResponseEntity<Object> createShoppingList(@Valid @RequestBody ShoppingListDto slDto, BindingResult bindingResult){

        ResponseEntity<Object> errorResponse = buildErrorResponse(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        Long shoppingListId = slService.createShoppingList(slDto);
        slDto.shoppingListId = shoppingListId;

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + shoppingListId).toUriString());

        return ResponseEntity.created(uri).body(slDto);

    }

    @GetMapping("/{shoppingListId}")
    public  ResponseEntity<ShoppingListDto> getShoppingList(@PathVariable Long shoppingListId){
        ShoppingListDto sLdto = (ShoppingListDto) slService.getShoppingList(shoppingListId);

        return ResponseEntity.ok(sLdto);
    }


}
