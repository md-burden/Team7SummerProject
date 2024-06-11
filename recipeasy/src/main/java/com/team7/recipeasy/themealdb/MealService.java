package com.team7.recipeasy.themealdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
import com.team7.recipeasy.recipe.ingredients.IngredientService;
import com.team7.recipeasy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class MealService {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;

    public List<Meal> searchMealByName(String mealName) {
        String urlString = BASE_URL + "search.php?s=" + mealName;
        return getSearchMeals(urlString, mealName);
    }

    public Meal getMealById(String mealId) {
        String urlString = BASE_URL + "lookup.php?i=" + mealId;
        List<Meal> meals = getMealList(urlString, mealId);
        return meals.isEmpty() ? null : meals.getFirst();
    }

    private List<Meal> getMealList(String urlString, String mealId) {
        List<Meal> meals = new ArrayList<>();
        HttpURLConnection connection = null;
        BufferedReader in = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP GET Request Failed with Error code : " + responseCode);
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            ObjectMapper mapper = new ObjectMapper();

            JsonNode jsonNode = mapper.readTree(response.toString());
            if(jsonNode.get("meals") != null && !jsonNode.get("meals").isNull()){
                ArrayNode mealsNode = (ArrayNode) jsonNode.get("meals");
                if (mealsNode == null) {
                    throw new IOException("No meals found.");
                }

                for (JsonNode node : mealsNode) {
                    Meal meal = mapper.convertValue(node, Meal.class);
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int i = 1; i <= 20; i++) {
                        String ingredient = node.get("strIngredient" + i).asText();
                        String measure = node.get("strMeasure" + i).asText();
                        Ingredient ingredientObject = new Ingredient(ingredient, measure);
                        if (!ingredient.isEmpty() && !ingredient.equals("null")) {
                            ingredients.add(ingredientObject);
                        }
                    }
                    meal.setIngredients(ingredients);
                    meals.add(meal);
                }
            } else {
                Recipe recipe = recipeService.getRecipeById(parseInt(mealId));
                // TODO: need to add ingredients to meal when ingredientService is implemented.
                // List<Ingredient> ingredients = ingredientService.getIngredientsById(mealId);
                Meal meal = new Meal(String.valueOf(recipe.getRecipeId()), recipe.getRecipeTitle(), recipe.getRecipeInstructions(), recipe.getRecipeImage(), recipe.getRecipeCountry(), recipe.getRecipeType());
                meals.add(meal);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return meals;
    }

    private List<Meal> getSearchMeals(String urlString, String mealName) {
        List<Meal> meals = new ArrayList<>();
        HttpURLConnection connection = null;
        BufferedReader in = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP GET Request Failed with Error code : " + responseCode);
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.toString());

            if(jsonNode.get("meals") != null && !jsonNode.get("meals").isNull()) {
                ArrayNode mealsNode = (ArrayNode) jsonNode.get("meals");
                if (mealsNode == null) {
                    throw new IOException("No meals found.");
                }

                for (JsonNode node : mealsNode) {
                    Meal meal = mapper.convertValue(node, Meal.class);
                    List<Ingredient> ingredients = new ArrayList<>();
                    for (int i = 1; i <= 20; i++) {
                        String ingredient = node.get("strIngredient" + i).asText();
                        String measure = node.get("strMeasure" + i).asText();
                        Ingredient ingredientObject = new Ingredient(ingredient, measure);
                        if (!ingredient.isEmpty() && !ingredient.equals("null")) {
                            ingredients.add(ingredientObject);
                        }
                    }
                    meal.setIngredients(ingredients);
                    meals.add(meal);
                }
                List<Recipe> recipes = recipeService.searchRecipesByTitle(mealName);
                for(Recipe recipe: recipes) {
                    // TODO: need to add ingredients once IngredientService is complete
                    // List<Ingredient> ingredients = ingredientService.getIngredientsById(recipe.getRecipeId());
                    Meal newMeal = new Meal(String.valueOf(recipe.getRecipeId()), recipe.getRecipeTitle(), recipe.getRecipeInstructions(), recipe.getRecipeImage(), recipe.getRecipeCountry(), recipe.getRecipeType());
                    meals.add(newMeal);
                }
            } else {
                throw new IOException("No meals found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return meals;
    }
}