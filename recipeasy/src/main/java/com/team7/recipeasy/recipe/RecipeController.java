package com.team7.recipeasy.recipe;

import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;

    @GetMapping("/create")
    public String newRecipePage(@RequestParam("userId") int userId, Model model) {
        model.addAttribute("userId", userId);
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "Creator/createrecipe";
    }

    @PostMapping("/create")
    public String createNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam("userId") int userId) {
        if (recipe.getIngredients() != null) {
            System.out.println(recipe.getIngredients().toString()); // Check ingredients
        } else {
            System.out.println("Ingredients list is null"); // Add this line for debugging
        }

        recipeService.createNewRecipe(recipe, userId, recipe.getIngredients());
        return "redirect:/recipe/recent?userId=" + userId;
    }

    // TODO: Implement recipe updating
    @PostMapping("/update")
    public Object updateRecipe(@RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    /**
     * Navigates to a given creators recipes page
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/creator/all")
    public String getAllRecipes(@RequestParam(name = "userId", required = true) int userId, Model model){
        model.addAttribute("recipes", recipeService.getAllRecipesByCreatorId(userId));
        return "Creator/creatorallrecipes";
    }


    /**
     * Navigates to a give creators home page and shows 10 most recent recipes
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/recent")
    public String getRecentRecipes(@RequestParam(value = "userId", required = true) int userId, Model model){
        model.addAttribute("recentsList", recipeService.getRecentCreatorRecipes(userId));
        return "Creator/creatorhomepage";
    }

    /**
     * Navigates a recipe page and loads the given recipe
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("")
    public String getRecipe(@RequestParam(value = "recipeId", required = true) int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        model.addAttribute("comments", commentService.fetchAllCommentsByRecipeId(recipeId));
        return "Creator/creatorrecipepage";
    }

    @GetMapping("/totalSaves/{id}")
    public int getTotalSavesById(@PathVariable int id){
        return recipeService.getRecipeCountById(id);
    }

    /**
     * Deletes a given recipe
     * @param recipeId
     * @return
     */
    @GetMapping("/delete")
    public String deleteRecipe(@RequestParam(name = "recipeId", required = true) int recipeId){
        int userId = recipeService.getRecipeById(recipeId).getUser().getUserId();
        recipeService.deleteRecipeById(recipeId);
        return "redirect:/recipe/recent?userId=" + userId;
    }
}
