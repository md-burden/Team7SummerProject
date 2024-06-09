package com.team7.recipeasy.recipe;

import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserRepository;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Saves a new recipe to the database
     * @param recipe
     */
    public void createNewRecipe(Recipe recipe){
        User user = userRepository.findById(recipe.getUser().getUserId()).orElse(null);
        recipe = new Recipe(recipe, user);
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe){
        recipe = new Recipe(recipe);
        recipeRepository.save(recipe);
    }

    public  Object getAllRecipes(){
        return recipeRepository.findAll();
    }

    public  Object getAllRecipesByCreatorId(int id){
        return recipeRepository.findAllCreatorRecipe(id);
    }

    /**
     * Fetches the 5 most recent recipes for a particular creator
     * @param userId
     * @return
     */
    public List<Recipe> getRecentCreatorRecipes(int userId){
        return recipeRepository.findCreatorRecent(userId);
    }

    public int getRecipeById(int id){
        Recipe r = recipeRepository.findById(id).orElse(null);
        if (r != null){
            return r.getTotalSaves();
        }
        else{
            return -1;
        }
    }
  
     public void deleteRecipeById(int id){
        recipeRepository.deleteById(id);
    }

    public List<Integer> getRecipeIdByUserId(int userId){
        return recipeRepository.getRecipeIdByUserId(userId);
    }

    /**
     * Fetches the ID given the recipe name
     * @param name
     * @return ID
     */
    public Integer getRecipeIdByRecipeName(String name){
        return recipeRepository.getRecipeIdByRecipeName(name);
    }
}
