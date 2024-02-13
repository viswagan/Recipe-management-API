package com.example.Recipe.utils;

import com.example.Recipe.model.request.RecipeRequest;
import com.example.Recipe.model.response.RecipeResponse;

import com.example.Recipe.util.SerializationHelper;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class RecipeUtil {

    public static RecipeRequest readResourceAsRecipeRequest(String resourceName) throws Exception {
        try(InputStream stream = RecipeUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), RecipeRequest.class);
        }
    }

    public static RecipeResponse readResourceAsRecipeResponse(String resourceName) throws Exception {
        try(InputStream stream = RecipeUtil.class.getClassLoader().getResourceAsStream(resourceName)) {
            return SerializationHelper.toObject(IOUtils.toString(stream, UTF_8), RecipeResponse.class);
        }
    }
}
