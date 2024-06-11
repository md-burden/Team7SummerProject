package com.team7.recipeasy.creator;

import com.team7.recipeasy.comment.Comment;
import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/CREATOR")
public class CreatorContoller {
    @Autowired
    RecipeService recipeService;

    @Autowired
    CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String newRecipePage(@RequestParam(name = "userId") int userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("recipe", new Recipe());
        return "Creator/createrecipe";
    }

    @PostMapping("/create")
    public String createNewRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam("userId") int userId) {
        recipeService.createNewRecipe(recipe, userId);
        return "redirect:/home?userId=" + userId;
    }


    @GetMapping("/update")
    public String updateRecipePage(@RequestParam(name = "userId") int userId, @RequestParam(name = "recipeId") int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        return "Creator/creatorupdaterecipe";
    }

    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam(name = "userId") int userId, @RequestParam(name="recipeId") int recipeId){
        recipeService.updateRecipe(recipe, userId, recipeId);
        return "redirect:?recipeId=" + recipe.getRecipeId();
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
        return "redirect:/home?userId=" + userId;
    }

    /**
     * Navigates to a given creators recipes page
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/all")
    public String getAllRecipes(@RequestParam(name = "userId", required = true) int userId, Model model){
        model.addAttribute("recipes", recipeService.getAllRecipesByCreatorId(userId));
        return "Creator/creatorallrecipes";
    }


    /**
     * Navigates to a give creators home page and shows 10 most recent recipes
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String getRecentRecipes(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername()).orElse(null);
        assert user != null;
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("recentsList", recipeService.getRecentCreatorRecipes(user.getUserId()));
        return "Creator/creatorhomepage";
    }

    @PostMapping("/reply")
    public String replyToComment(@ModelAttribute(name = "comment") Comment comment){
        commentService.saveComment(comment);
        return "redirect:?recipeId=" + comment.getRecipe().getRecipeId();
    }
}
