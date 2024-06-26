package com.team7.recipeasy;

import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.reports.Report;
import com.team7.recipeasy.reports.ReportService;
import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import com.team7.recipeasy.user.UserStatsDisplay;
import com.team7.recipeasy.user.favorites.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    FavoriteService favoriteService;
    @Autowired
    RecipeService recipeService;


    /**
     * For User
     * @param model
     * @return
     */
    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String home(Model model){
        return "BasePages/LoginPage";
    }

    /**
     * Security Login
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "BasePages/LoginPage";
    }

    @GetMapping("/createAccount")
    public String createAccount(){
        return "BasePages/CreateAccountPage";
    }

    @GetMapping("/forgotpassword")
    public String resetPassword(Model model){
        model.addAttribute("user", new User());
        return "BasePages/ForgotPassword";
    }

    @PostMapping("/forgotpassword")
    public String resetPassword(@ModelAttribute("user") User user){
        userService.resetPassword(user);
        return "redirect:/login";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }

    /**
     * Admin Home Page
     * @param model
     * @return
     */
    @GetMapping("/ADMIN/home")
    public String getAdminHomePage(@RequestParam(name = "continue", required = false) String cont, Model model){
        return "Admin/AdminHome";
    }

    /**
     * Admin Reports Page
     * @param model
     * @return
     */
    @GetMapping("/ADMIN/reports")
    public String getAdminReportsPage(Model model){
        model.addAttribute("reportList", reportService.getAllReports());

        return "Admin/AdminReportsPage";
    }

    /**
     * Admin stats page displays total statistics
     * @param model
     * @return html location
     */
    @GetMapping("/ADMIN/stats")
    public String getAdminStatsPage(Model model){
        model.addAttribute("userCount", userService.getActiveUserCountByAcctType(Role.USER));
        model.addAttribute("creatorCount", userService.getActiveUserCountByAcctType(Role.CREATOR));
        model.addAttribute("adminCount", userService.getActiveUserCountByAcctType(Role.ADMIN));
        model.addAttribute("bannedCount", userService.getBannedUserCount());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRecipes", recipeService.getAllRecipes());
        model.addAttribute("stats", new UserStatsDisplay());

        return "Admin/AdminStatsPage";
    }

    /**
     * Used to load page for the first time
     * @param term
     * @param model
     * @return
     */
    @GetMapping("/ADMIN/accounts")
    public String getAccountsPage(String term, Model model){
        model.addAttribute("userList", userService.getUsersFromSearch(term));
        return "Admin/AdminSearchPage";
    }


    /**
     * Used to load page with search terms
     * @param term
     * @param model
     * @return
     */
    @PostMapping("/ADMIN/accounts")
    public String AccountsPage(@RequestParam(value = "term", required = true) String term, Model model){
        model.addAttribute("userList", userService.getUsersFromSearch(term));
        return "Admin/AdminSearchPage";
    }

    @GetMapping("/ADMIN/allComments")
    public String getAllComments(Model model){
        model.addAttribute("commentList", commentService.fetchAllComments());
        return "Admin/AdminCommentsPage";
    }

    /**
     * Called from the stat page search, this method finds the creator by username and sets the username, favorites
     * count, and comments count to the Stats Display static class. If the creator does not exist, the username is
     * set to a warning message
     * @param term is the username of the creator.
     * @return a redirect to the stats page that displays the info.
     */
    @PostMapping("/ADMIN/updateUserStatSearch")
    public String updateUserStatSearch(@RequestParam(value = "term", required = true) String term){
        User current = userService.findUserByUsername(term).orElse(null);
        if (current == null || current.getRole() != Role.CREATOR){
            UserStatsDisplay.setUsername("Creator Does Not Exist");
            UserStatsDisplay.setTotalCreatorSaves(0);
            UserStatsDisplay.setTotalCreatorComments(0);
        }
        else{
            int id = current.getUserId();
            UserStatsDisplay.setUsername(term);
            UserStatsDisplay.setTotalCreatorSaves(favoriteService.getFavoriteCountByUserId(id));
            UserStatsDisplay.setTotalCreatorComments(commentService.getCommentCountByUser(id));
        }
        return "redirect:/ADMIN/stats";
    }

    /**
     * Called from the stat page search, this method finds the recipe by username and sets the title, favorites
     * count, and comments count to the Stats Display static class. If the recipe does not exist, the title is
     * set to a warning message
     * @param recipeTerm is the name of the recipe.
     * @return a redirect to the stats page that displays the info.
     */
    @PostMapping("/ADMIN/updateRecipeStatSearch")
    public String updateRecipeStatSearch(@RequestParam(value = "recipeTerm", required = true) String recipeTerm){
        Integer current = recipeService.getRecipeIdByRecipeName(recipeTerm);
        if (current == null){
            UserStatsDisplay.setRecipeTitle("Recipe Does Not Exist");
            UserStatsDisplay.setTotalRecipeComments(0);
            UserStatsDisplay.setTotalRecipeSaves(0);
        }
        else{
            UserStatsDisplay.setRecipeTitle(recipeTerm);
            UserStatsDisplay.setTotalRecipeSaves(favoriteService.getFavoriteCountByRecipeId(current));
            UserStatsDisplay.setTotalRecipeComments(commentService.getCommentCountByRecipe(current));
        }
        return "redirect:/ADMIN/stats";
    }

    @GetMapping("/ADMIN/profile")
    public String getProfilePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
//        model.addAttribute("email", u.getEmail());
//        model.addAttribute("role", u.getRole());


        return "Admin/AdminProfilePage";
    }

    @GetMapping("/ADMIN/recipe/{recipeId}")
    public String getRecipe(@PathVariable int recipeId, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
        model.addAttribute("comments", commentService.fetchAllCommentsByRecipeId(recipeId));
        model.addAttribute("report", new Report());
        return "Creator/recipepage";
    }




}
