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
    public void createNewRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe){
        recipe = new Recipe(recipe);

        recipeRepository.save(recipe);
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

    public void deleteRecipeById(int id){
        recipeRepository.deleteById(id);
    }

    public String getRecipeStats(int recipeId){
        return recipeRepository.getRecipeStats(recipeId);
    }
}
