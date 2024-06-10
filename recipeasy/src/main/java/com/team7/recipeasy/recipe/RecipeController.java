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

    @GetMapping("/CREATOR/create")
    public String newRecipePage(@RequestParam(name = "userId") int userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("recipe", new Recipe());
        return "Creator/createrecipe";
    }

    @PostMapping("/CREATOR/create")
    public String createNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam("userId") int userId) {
        recipeService.createNewRecipe(recipe, userId);
        return "redirect:/recipe/CREATOR/recent?userId=" + userId;
    }


    @GetMapping("/CREATOR/update")
    public String updateRecipePage(@RequestParam(name = "userId") int userId, @RequestParam(name = "recipeId") int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        return "Creator/creatorupdaterecipe";
    }

    @PostMapping("/CREATOR/update")
    public String updateRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam(name = "userId") int userId, @RequestParam(name="recipeId") int recipeId){
        recipeService.updateRecipe(recipe, userId, recipeId);
        return "redirect:/recipe?recipeId=" + recipe.getRecipeId();
    }

    /**
     * Deletes a given recipe
     * @param recipeId
     * @return
     */
    @GetMapping("/CREATOR/delete")
    public String deleteRecipe(@RequestParam(name = "recipeId", required = true) int recipeId){
        int userId = recipeService.getRecipeById(recipeId).getUser().getUserId();
        recipeService.deleteRecipeById(recipeId);
        return "redirect:/recipe/CREATOR/recent?userId=" + userId;
    }

    /**
     * Navigates to a given creators recipes page
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/CREATOR/all")
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
    @GetMapping("/CREATOR/recent")
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

    @PostMapping("/CREATOR/reply")
    public String replyToComment(@ModelAttribute(name = "comment")Comment comment){
        commentService.saveComment(comment);
        return "redirect:/recipe?recipeId=" + comment.getRecipe().getRecipeId();
    }

    @GetMapping("/totalSaves/{id}")
    public int getTotalSavesById(@PathVariable int id){
        return recipeService.getRecipeCountById(id);
    }

//    IF SHTF; UNCOMMENT
//    @GetMapping("/all")
//    public Object findAllRecipes(){
//        return recipeService.getAllRecipes();
//    }
//
//    @GetMapping("/all/{id}")
//    public Object getAllRecipes(@PathVariable int id) {
//        return recipeService.getAllRecipesByCreatorId(id);
//    }
  
}
