package com.team7.recipeasy.recipe;

import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
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
    private UserService userService;
    @Autowired
    private CommentService commentService;

    /**
     * Saves a new recipe to the database
     * @param recipe
     */
    public void createNewRecipe(Recipe recipe, int userId){
        User user = userService.getUserById(userId);
        recipe = new Recipe(recipe, user);
        recipeRepository.save(recipe);
    }

    public void updateRecipe(Recipe recipe, int userId, int recipeId){
        recipe.setRecipeId(recipeId);
        User user = userService.getUserById(userId);
        recipe = new Recipe(recipe, user);

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
    public Object getRecentCreatorRecipes(int userId){
        return recipeRepository.findCreatorRecent(userId);
    }

    public Recipe getRecipeById(int id){
        return recipeRepository.findById(id).orElse(null);
    }

    public int getRecipeCountById(int id){
        Recipe r = recipeRepository.findById(id).orElse(null);
        if (r != null){
            return r.getTotalSaves();
        }
        else{
            return -1;
        }
    }
  
     public void deleteRecipeById(int recipeId){
        commentService.deleteCommentsByRecipeId(recipeId);
        recipeRepository.deleteById(recipeId);
    }

    public int getRecipeCountByUserId(int userId){
        return recipeRepository.getRecipeCountByUserId(userId);
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
