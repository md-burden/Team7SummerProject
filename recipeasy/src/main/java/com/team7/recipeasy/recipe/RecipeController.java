package com.team7.recipeasy.recipe;

import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String createNewRecipe(@RequestBody Recipe recipe, @RequestParam(value = "userId", required = true) int userId){
        recipeService.createNewRecipe(recipe, userId);
        return "redirect:/recipe/all";
    }

    @GetMapping("/all")
    public Object getAllRecipes(){
        return recipeService.getAllRecipes();
    }


    @GetMapping("/recent")
    public List<Recipe> getRecentRecipes(@RequestParam(value = "userId", required = true) int userId){
        return recipeService.getRecentCreatorRecipes(userId);
    }
}
