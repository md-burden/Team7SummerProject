package com.team7.recipeasy.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/create")
    public String createNewRecipe(@RequestBody Recipe recipe){
        recipeService.createNewRecipe(recipe);
        return "redirect:/recipe/all";
    }

    @GetMapping("/all")
    public Object getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/totalSaves/{id}")
    public int getTotalSavesById(@PathVariable int id){
        Recipe r = recipeService.getRecipeById(id);
        if (r != null){
            return r.getTotalSaves();
        }
        else{
            return -1;
        }
    }

}
