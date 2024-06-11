package com.team7.recipeasy.recipe;

import com.team7.recipeasy.comment.Comment;
import com.team7.recipeasy.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;


    /**
     * Finds a Recipe by the recipe ID. Loads the recipe page with the found recipe.
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("")
    public String getRecipe(@RequestParam(value = "recipeId", required = true) int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        model.addAttribute("comments", commentService.fetchAllCommentsByRecipeId(recipeId));
        return "recipepage";
    }

    /**
     * Returns the total saves for a give Recipe by the recipe ID.
     * @param id
     * @return
     */
    @GetMapping("/totalSaves/{id}")
    public int getTotalSavesById(@PathVariable int id){
        return recipeService.getRecipeCountById(id);
    }
}
