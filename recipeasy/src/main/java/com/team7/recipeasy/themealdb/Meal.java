package com.team7.recipeasy.themealdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    // Getters and setters

    public Meal(String idMeal, String strMeal, String strInstructions, String strMealThumb, String strArea, String strCategory){
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strArea = strArea;
        this.strCategory = strCategory;
    }
    public Meal(){
        this.idMeal = "";
        this.strMeal = "";
        this.strInstructions = "";
        this.strMealThumb = "";
        this.strArea = "";
        this.strCategory = "";
    }


}
