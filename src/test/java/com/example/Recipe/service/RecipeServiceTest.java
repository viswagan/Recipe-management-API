package com.example.Recipe.service;

import com.example.Recipe.exception.RecipeNotFoundException;
import com.example.Recipe.model.Diet;
import com.example.Recipe.model.Recipe;
import com.example.Recipe.repository.RecipeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RecipeServiceTest {


    @Test
    public void testNonExistingRecipe() throws Exception {
        var testRecipeID = 100L;
        var recipeRep = mock(RecipeRepository.class);
        when(recipeRep.findById(testRecipeID)).thenReturn(Optional.empty());

        var subjectUnderTest = new RecipeService(recipeRep);
        var exception = assertThrows(RecipeNotFoundException.class, () -> {
            subjectUnderTest.getRecipeById(testRecipeID);
        });
        assertEquals("%s".formatted("Recipe with the id {} not found not found with 100"), exception.getMessage());
    }

    @Test
    public void testRecipeWithExistingID() throws Exception {
        var testRecipeID = 10L;
        var recipeRep = mock(RecipeRepository.class);
        when(recipeRep.findById(testRecipeID)).thenReturn(Optional.of(createRecipeObject()));

        var subjectUnderTest = new RecipeService(recipeRep);
        var recipe = subjectUnderTest.getRecipeById(testRecipeID);

        assertEquals("testRecipe",recipe.getName());
    }

//    @Test
//    public void testGetAllRecipes() {
//        var recipeRep = mock(RecipeRepository.class);
//        var subjectUnderTest = new RecipeService(recipeRep);
//
//        List<Recipe> recipeList = Collections.singletonList(createRecipeObject());
//        when(recipeRep.findAll()).thenReturn(recipeList);
//        List<Recipe> recipesList = subjectUnderTest.getAllRecipes();
//        assertThat(recipesList).hasSize(1);
//    }

    @Test
    public void testCreateRecipe() {

        var recipeRep = mock(RecipeRepository.class);
        var subjectUnderTest = new RecipeService(recipeRep);

        when(recipeRep.save(createRecipeObject())).thenReturn(savedRecipeObject());
        Recipe recipesList = subjectUnderTest.createRecipe(createRecipeObject());
        assertThat(recipesList.getName()).isEqualTo("testRecipe");
        assertThat(recipesList.getId()).isEqualTo(10L);
    }

    @Test
    public void testUpdateRecipeById() {

        var recipeRep = mock(RecipeRepository.class);
        var subjectUnderTest = new RecipeService(recipeRep);

        Recipe recipe = savedRecipeObject();
        when(recipeRep.findById(recipe.getId())).thenReturn(Optional.of(recipe));

        recipe.getIngredients().add("butter");
        when(recipeRep.save(recipe)).thenReturn(recipe);
        Recipe recipesList = subjectUnderTest.updateRecipe(recipe);
        assertThat(recipesList.getIngredients()).contains("butter");
    }

//    @Test
//    void deleteRecipeById() {
//
//        var recipeRep = mock(RecipeRepository.class);
//        var subjectUnderTest = new RecipeService(recipeRep);
//
//        Recipe recipe = savedRecipeObject();
//        long recipeId = recipe.getId();
//        doNothing().when(recipeRep).deleteById(recipeId);
//        when(recipeRep.existsById(recipeId)).thenReturn(true);
//
//        Boolean result = subjectUnderTest.deleteRecipe(recipeId);
//        verify(subjectUnderTest, times(1)).deleteRecipe(recipeId);
//        assertTrue(result, "Recipe deleted, returns TRUE");
//    }

//    @Test
//    void deleteRecipeById_RecipeDoesNotExist() {
//
//        var recipeRep = mock(RecipeRepository.class);
//        var subjectUnderTest = new RecipeService(recipeRep);
//
//        Long nonExistingRecipeId = UUID.randomUUID().getMostSignificantBits();
//
//        when(recipeRep.existsById(nonExistingRecipeId)).thenReturn(false);
//
//
//        Boolean result = subjectUnderTest.deleteRecipe(nonExistingRecipeId);
//
//        // Assert
//        verify(subjectUnderTest, never()).deleteRecipe(nonExistingRecipeId);
//        assertFalse(result, "The recipe should not be deleted and return FALSE");
//    }
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
