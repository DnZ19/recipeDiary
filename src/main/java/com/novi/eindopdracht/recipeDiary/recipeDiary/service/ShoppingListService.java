package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.ShoppingListDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.ShoppingList;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingRepo;

    public ShoppingListService(ShoppingListRepository shoppingRepo){
        this.shoppingRepo = shoppingRepo;
    }

    public Long createShoppingList(ShoppingListDto sldto){

        ShoppingList s = new ShoppingList();
        s.setItems(sldto.items);

        ShoppingList shoppingList = shoppingRepo.findById(sldto.shoppingListId).get();
        s.setShoppingList(shoppingList);

        shoppingRepo.save(s);

        return s.getShoppingListId();
    }

    public ShoppingListDto getShoppingList(Long shoppingListId){
        ShoppingList s = shoppingRepo.findById(shoppingListId).orElseThrow(()-> new ResourceNotFoundException("Cant find the shoppingList"));

        ShoppingListDto sldto = new ShoppingListDto();

        sldto.shoppingListId = s.getShoppingListId();
        sldto.items = s.getItems();

        return sldto;

    }

}
