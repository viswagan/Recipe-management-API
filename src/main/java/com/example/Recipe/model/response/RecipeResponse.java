package com.example.Recipe.model.response;

import com.example.Recipe.model.Diet;

import java.util.List;

public record RecipeResponse (long id,
                             String name,
                             List<String> instructions,
                             List<String> ingredients,
                             int servings,
                             Diet diet){
}
