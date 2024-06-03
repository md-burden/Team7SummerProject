package com.team7.recipeasy.recipe;

import com.team7.recipeasy.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Object  createNewRecipe(@RequestBody Recipe recipe )  {
        recipeService.createNewRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    @PostMapping("/update")
    public Object updateRecipe(@RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe);
        return recipeService.getAllRecipes();
    }

    @GetMapping("/all/{id}")
    public Object getAllRecipes(@PathVariable int id){
        return recipeService.getAllRecipesByCreatorId(id);
    }


    @GetMapping("/recent")
    public List<Recipe> getRecentRecipes(@RequestParam(value = "userId", required = true) int userId){
        return recipeService.getRecentCreatorRecipes(userId);
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
