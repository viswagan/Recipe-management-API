package com.example.Recipe.controller;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import com.example.Recipe.model.Recipe;
import java.util.*;

import static com.example.Recipe.utils.RecipeFactory.getRecipe;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeControllerIntegrationTest {
    private static final String HOST_NAME = "http://localhost:";
    public static Recipe recipe;
    private static TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    public RecipeControllerIntegrationTest() {
        super();
    }

    @BeforeAll
    public static void init() {
        restTemplate = new TestRestTemplate();
    }

    @BeforeEach
    public void setUp() {
        recipe = getRecipe();
        restTemplate.postForEntity(HOST_NAME + port + "/recipes", recipe, Recipe.class);

    }

    @Test
    void addRecipe() {
        Recipe recipe = getRecipe();

        ResponseEntity<Recipe> recipeResponse = restTemplate.postForEntity(HOST_NAME + port + "/recipes", recipe, Recipe.class);
        assertThat(Objects.requireNonNull(recipeResponse.getBody()).getId()).isNotNull();
        assertThat(recipeResponse.getBody().getIngredients()).hasSize(4);
        assertThat(recipeResponse.getBody().getName()).isEqualTo("Vada pav");
    }

    @Test
        void getAllRecipes() {
        ResponseEntity<List> recipes = restTemplate.getForEntity(HOST_NAME + port + "/recipes", List.class);
        assertThat(recipes.getBody()).isNotEmpty();
        assertThat(recipes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateRecipeById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Recipe>> recipes = restTemplate.exchange(HOST_NAME + port + "/recipes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Recipe>>() {
        });
        assertThat(recipes.getBody()).isNotEmpty();
        recipes.getBody().get(0).getIngredients().add("butter");
        assertThat(restTemplate.exchange(HOST_NAME + port + "/recipes", HttpMethod.PUT, ResponseEntity.ok(recipes.getBody().get(0)), String.class).getBody()).isEqualTo("Recipe updated successfully.");
        assertThat(recipes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void removeRecipeById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Recipe>> recipes = restTemplate.exchange(HOST_NAME + port + "/recipes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Recipe>>() {
        });
        assertThat(recipes.getBody()).isNotEmpty();
        assertThat(restTemplate.exchange(HOST_NAME + port + "/recipes/" + recipes.getBody().get(0).getId(), HttpMethod.DELETE, null, String.class).getBody()).isEqualTo("Recipe deleted successfully.");
        assertThat(recipes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
