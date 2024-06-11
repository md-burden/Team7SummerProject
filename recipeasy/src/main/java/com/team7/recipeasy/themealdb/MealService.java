package com.team7.recipeasy.themealdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public List<Meal> searchMealByName(String mealName) {
        String urlString = BASE_URL + "search.php?s=" + mealName;
        return getMealsFromUrl(urlString);
    }

    public Meal getMealById(String mealId) {
        String urlString = BASE_URL + "lookup.php?i=" + mealId;
        List<Meal> meals = getMealsFromUrl(urlString);
        return meals.isEmpty() ? null : meals.getFirst();
    }

    private List<Meal> getMealsFromUrl(String urlString) {
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

            ArrayNode mealsNode = (ArrayNode) jsonNode.get("meals");
            System.out.println(mealsNode);
            if (mealsNode == null) {
                throw new IOException("No meals found.");
            }

            for (JsonNode node : mealsNode) {
                Meal meal = mapper.convertValue(node, Meal.class);
               // Meal meal = new Meal(node.get("idMeal").asText(),node.get("strMeal").asText(),node.get("strInstructions").asText(),node.get("strMealThumb").asText(),node.get("strArea").asText());
                meals.add(meal);
                //System.out.println(meal);
            }
            System.out.println(meals);
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