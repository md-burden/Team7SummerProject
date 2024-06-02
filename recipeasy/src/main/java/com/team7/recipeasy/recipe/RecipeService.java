package com.team7.recipeasy.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    /**
     * Saves a new recipe to the database
     * @param recipe
     */
    public void createNewRecipe(Recipe recipe, int userId){
        recipeRepository.save(recipe);
    }

    // TODO: Implement update method
    public void updateRecipe(Recipe recipe){

    }

    public  Object getAllRecipes(){
        return recipeRepository.findAll();
    }

    /**
     * Fetches the 5 most recent recipes for a particular creator
     * @param userId
     * @return
     */
    public List<Recipe> getRecentCreatorRecipes(int userId){
        return recipeRepository.findCreatorRecent(userId);
    }
}
