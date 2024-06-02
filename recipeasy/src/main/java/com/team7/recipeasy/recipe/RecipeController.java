package com.team7.recipeasy.recipe;

import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Object createNewRecipe(@RequestBody Recipe recipe){
        recipeService.createNewRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    @PostMapping("/update")
    public Object updateRecipe(@RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    @GetMapping("/all")
    public Object getAllRecipes(){
        return recipeService.getAllRecipes();
    }


    @GetMapping("/recent")
    public List<Recipe> getRecentRecipes(@RequestParam(value = "userId", required = true) int userId){
        return recipeService.getRecentCreatorRecipes(userId);
    }

    @GetMapping("/delete")
    public void deleteRecipe(@RequestParam(value = "recipeId", required = true) int recipeId){
        recipeService.deleteRecipeById(recipeId);
    }

    @GetMapping("/recipestats")
    public String getRecipeStats(@RequestParam(value = "recipeId", required = true) int recipeId){
        return recipeService.getRecipeStats(recipeId);
    }
}
