package com.example.Recipe.utils;

import com.example.Recipe.model.Diet;
import lombok.experimental.UtilityClass;
import org.assertj.core.util.Lists;

import com.example.Recipe.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class RecipeFactory {

    public static Recipe getRecipe() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Flour");
        ingredients.add("Sugar");
        return Recipe.builder()
                .id(10L)
                .name("testRecipe")
                .diet(Diet.VEGETARIAN)
                .ingredients(ingredients)
                .instructions(List.of("Mix them all"))
                .servings(4)
                .build();

    }

    public static Recipe getSavedRecipe() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Flour");
        ingredients.add("Sugar");
        return Recipe.builder()
                .id(10L)
                .name("testRecipe")
                .diet(Diet.VEGETARIAN)
                .ingredients(ingredients)
                .instructions(List.of("Mix them all"))
                .servings(4)
                .build();

    }

    private Recipe createRecipeObject(){

        return Recipe.builder()
                .id(10L)
                .name("testRecipe")
                .diet(Diet.VEGETARIAN)
                .ingredients(List.of("flour","sugar"))
                .instructions(List.of("Mix them all"))
                .servings(4)
                .build();
    }

    private Recipe savedRecipeObject(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Flour");
        ingredients.add("Sugar");
        return Recipe.builder()
                .id(10L)
                .name("testRecipe")
                .diet(Diet.VEGETARIAN)
                .ingredients(ingredients)
                .instructions(List.of("Mix them all"))
                .servings(4)
                .build();

    }
}
