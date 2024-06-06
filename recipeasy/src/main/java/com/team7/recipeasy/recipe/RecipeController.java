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

    @GetMapping("/create")
    public String newRecipePage(@RequestParam(name="userId", required = true)int userId, Model model){
        model.addAttribute("userId", userId);
        return "Creator/RecipeCreationPage";
    }

    @PostMapping("/create")
    public String  createNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam(name="userId", required = true) int userId)  {
        recipeService.createNewRecipe(recipe, userId);
        // TODO: Switch to RecipePage but I'm being lazy for the moment
        return "redirect:/recipe/recent?userId=" + userId;
    }

    @PostMapping("/update")
    public Object updateRecipe(@RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    @GetMapping("/all")
    public String getAllRecipes(@RequestParam(name = "userId", required = true) int userId, Model model){
        model.addAttribute("recipes", recipeService.getAllRecipesByCreatorId(userId));
        return "Creator/CreatorRecipes";
    }


    @GetMapping("/recent")
    public String getRecentRecipes(@RequestParam(value = "userId", required = true) int userId, Model model){
        model.addAttribute("recentsList", recipeService.getRecentCreatorRecipes(userId));
        return "Creator/CreatorHomePage";
    }

    @GetMapping("")
    public String getRecipe(@RequestParam(value = "recipeId", required = true) int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        return "Creator/CreatorRecipePage";
    }

    @GetMapping("/totalSaves/{id}")
    public int getTotalSavesById(@PathVariable int id){
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/delete/{id}")
    public Object deleteRecipe(@PathVariable int id){
        recipeService.deleteRecipeById(id);
        return recipeService.getAllRecipes();
    }
}
