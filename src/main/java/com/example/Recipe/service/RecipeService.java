package com.example.Recipe.service;

import com.example.Recipe.exception.RecipeNotFoundException;
import com.example.Recipe.model.Recipe;
import com.example.Recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepository recipeRepository;


    public List<Recipe> getAllRecipes(@RequestParam(required = false) String includesIngredient,
                                      @RequestParam(required = false) String excludesIngredient) {
        log.info("Get all recipes, total recipes found {}", recipeRepository.findAll().size());
        List<Recipe> recipes = recipeRepository.findAll();

        if(includesIngredient != null && !includesIngredient.isEmpty()) {
            recipes = recipes.stream().filter(recipe -> recipe.getIngredients().contains(includesIngredient)).collect(Collectors.toList());
        }
        if(excludesIngredient != null && !excludesIngredient.isEmpty()){
            recipes = recipes.stream().filter(recipe -> !recipe.getIngredients().contains(excludesIngredient)).collect(Collectors.toList());
        }

        return recipes;
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe with the id {} not found",id));
    }

    public Recipe createRecipe(Recipe recipe) {
        log.info("Create recipe Operation");
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        log.info("Update Operation, recipe to be updated: {}",recipe.getId());
        return recipeRepository.save(recipe);
    }

    public Boolean deleteRecipe(Long id) {
        log.info("Delete recipe {}",id);
        if (recipeRepository.existsById(id)){
            recipeRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
