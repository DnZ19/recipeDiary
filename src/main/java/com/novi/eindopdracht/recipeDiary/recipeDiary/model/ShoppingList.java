package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long shoppingListId;
    // list van maken?
    private Enum items;

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public Enum getItems() {
        return items;
    }

    public void setItems(Enum items) {
        this.items = items;
    }

    // vabnuit de service gecreeerd...is dit wel juist?
    public void setShoppingList(ShoppingList shoppingList) {

    }
}
