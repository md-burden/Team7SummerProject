package com.team7.recipeasy.creator;

import com.team7.recipeasy.comment.Comment;
import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.constants.ReportOptions;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.reports.Report;
import com.team7.recipeasy.reports.ReportService;
import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/CREATOR")
public class CreatorController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    ReportService reportService;

    /**
     * Navigates to a give creators home page and shows 10 most recent recipes
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String getRecentRecipes(@AuthenticationPrincipal UserDetails userDetails, Model model){
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("recentsList", recipeService.getRecentCreatorRecipes(userId));
        return "Creator/homepage";
    }

    /**
     * Fetches all the recipes the currently authenticated creator and loads them in the all page
     * @param model
     * @return
     */
    @GetMapping("/all")
    public String getAllRecipes(@AuthenticationPrincipal UserDetails userDetails, Model model){
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
        model.addAttribute("recipes", recipeService.getAllRecipesByCreatorId(userId));
        return "Creator/allrecipes";
    }

    /**
     * Finds a Recipe by the recipe ID. Loads the creator recipe page with the found recipe.
     * This page differs from the user page since there is a need for an update button and replying to comments.
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("/recipe")
    public String getRecipe(@RequestParam(value = "recipeId", required = true) int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        model.addAttribute("comments", commentService.fetchAllCommentsByRecipeId(recipeId));
        model.addAttribute("report", new Report());
        return "Creator/recipepage";
    }

    @GetMapping("/create")
    public String newRecipePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("recipe", new Recipe());
        return "Creator/createrecipe";
    }

    @PostMapping("/create")
    public String createNewRecipe(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("recipe") Recipe recipe) {
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
        recipeService.createNewRecipe(recipe, userId);
        return "redirect:/CREATOR/home";
    }


    @GetMapping("/update")
    public String updateRecipePage(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "recipeId") int recipeId, Model model){
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        return "Creator/updaterecipe";
    }

    @PostMapping("/update")
    public String updateRecipe(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("recipe") Recipe recipe, @RequestParam(name="recipeId") int recipeId){
        int userId = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null)).getUserId();
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
        return "redirect:/CREATOR/home";
    }

    @PostMapping("/reply")
    public String replyToComment(@ModelAttribute(name = "comment") Comment comment){
        commentService.saveComment(comment);
        return "redirect:/CREATOR/recipe?recipeId=" + comment.getRecipe().getRecipeId();
    }

    @PostMapping("/report")
    public String reportComment(@ModelAttribute(name="report") Report report){
        reportService.saveReport(report);
        return "redirect:/CREATOR/recipe?recipeId=" + report.getComment().getRecipe().getRecipeId();
    }

    @GetMapping("/profile")
    public String loadHomePage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null));
        model.addAttribute("user", user);
        return "Creator/profilepage";
    }
}
