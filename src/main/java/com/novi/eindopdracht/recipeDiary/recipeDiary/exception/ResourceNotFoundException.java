package com.novi.eindopdracht.recipeDiary.recipeDiary.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String recipe, String id, Long recipeId) {

    }
}

