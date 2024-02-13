package com.example.Recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(String recipeName, Long id){
        super(String.format("%s not found with %s", recipeName, id));
    }
}
