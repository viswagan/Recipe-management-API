package com.example.Recipe;

import com.example.Recipe.model.Recipe;
import com.example.Recipe.util.SerializationHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class RecipeIntegrationTest {
    @Autowired
    protected MockMvc mvc;

    protected List<Recipe> testGetAllRecipes() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        return SerializationHelper.byTypeReference(response.getResponse().getContentAsString(), new TypeReference<>() {
        });
    }
}
