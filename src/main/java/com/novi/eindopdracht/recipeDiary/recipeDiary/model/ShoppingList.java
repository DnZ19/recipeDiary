package com.novi.eindopdracht.recipeDiary.recipeDiary.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long shoppingListId;
    // list van maken?
    private String items;

    @OneToOne(mappedBy = "shoppingList")
    private Recipe recipe;



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

    // vabnuit de service gecreeerd...is dit wel juist?
    public void setShoppingList(ShoppingList shoppingList) {

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
