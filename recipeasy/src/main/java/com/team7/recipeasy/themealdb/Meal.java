package com.team7.recipeasy.themealdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data

@Getter
@Setter
public class Meal {

    @JsonProperty("idMeal")
    private String idMeal;

    @JsonProperty("strMeal")
    private String strMeal;

    @JsonProperty("strInstructions")
    private String strInstructions;

    @JsonProperty("strMealThumb")
    private String strMealThumb;

    @JsonProperty("strArea")
    private String strArea;

    @JsonProperty("strCategory")
    private String strCategory;

    private List<Ingredient> ingredients;

    // Getters and setters
    // TODO: Add List<Ingredient> ingredients to Meal constructor
    public Meal(String idMeal, String strMeal, String strInstructions, String strMealThumb, String strArea, String strCategory){
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strArea = strArea;
        this.strCategory = strCategory;
        // TODO: Uncomment this once ingredients is added
//        this.ingredients = ingredients;
    }
    public Meal(){
        this.idMeal = "";
        this.strMeal = "";
        this.strInstructions = "";
        this.strMealThumb = "";
        this.strArea = "";
        this.strCategory = "";
        this.ingredients = new ArrayList<>();
    }


}
