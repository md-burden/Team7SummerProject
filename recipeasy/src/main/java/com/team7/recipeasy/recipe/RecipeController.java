package com.team7.recipeasy.recipe;

import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    /**
     * Directs user to the recipe creation page
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String newRecipePage(@RequestParam(name="userId", required = true)int userId, Model model){
        model.addAttribute("userId", userId);
        return "Creator/RecipeCreationPage";
    }

    /**
     * Adds the received [recipe] to the database
     * Redirects the user to the ...
     * @param recipe
     * @param userId
     * @return
     */
    @PostMapping("/create")
    public String  createNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam(name="userId", required = true) int userId)  {
        recipeService.createNewRecipe(recipe, userId);
        // TODO: Switch to RecipePage but I'm being lazy for the moment
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
        return "Creator/CreatorRecipes";
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
        return "Creator/CreatorHomePage";
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
        return "Creator/CreatorRecipePage";
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
