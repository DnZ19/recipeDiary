package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

public class ShoppingListDto {

    public Long shoppingListId;
    // list van maken?
    public String items;

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
