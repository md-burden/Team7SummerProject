package com.team7.recipeasy.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    /**
     * Saves a new recipe to the database
     * @param recipe
     */
    public void createNewRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe){

    }

    public  Object getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(int id){
        return recipeRepository.findById(id).orElse(null);
    }
}
