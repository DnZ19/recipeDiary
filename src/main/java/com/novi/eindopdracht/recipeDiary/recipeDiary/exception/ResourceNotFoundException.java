package com.novi.eindopdracht.recipeDiary.recipeDiary.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String recipe, String id, Long recipeId) {

    }
}

//package com.novi.eindopdracht.recipeDiary.recipeDiary.exception;
//
//public class ResourceNotFoundException extends RuntimeException {
//
//    public ResourceNotFoundException(String message) {
//        super(message);
//    }
//
//    public ResourceNotFoundException(String recipe, String id, Object recipeId) {
//        super(String.format("Resource '%s' not found for %s with value %s", recipe, id, recipeId));
//    }
//}

