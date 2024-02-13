package com.example.Recipe.model.request;

import com.example.Recipe.model.Diet;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public record RecipeRequest(long id,
                            String name,
                            List<String> instructions,
                            List<String> ingredients,
                            int servings,
                            Diet diet) {
}
