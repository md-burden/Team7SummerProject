package com.team7.recipeasy;

import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.reports.ReportService;
import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import com.team7.recipeasy.user.UserStatsDisplay;
import com.team7.recipeasy.user.userstats.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    @Autowired
    UserStatsService userStatService;

    @Autowired
    CommentService commentService;


    /**
     * For User
     * @param model
     * @return
     */
    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String home(Model model){
        return "home";
    }

    /**
     * Security Login
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "Base Pages/LoginPage";
    }

    /**
     * Admin Home Page
     * @param model
     * @return
     */
    @GetMapping("/ADMIN/home")
    public String getAdminHomePage(Model model){
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
     * Admin stats page
     * @param model
     * @return
     */
    @GetMapping("/ADMIN/stats")
    public String getAdminStatsPage(Model model){
        UserStatsDisplay usd = new UserStatsDisplay("", 0, 0, 0, "", 0, 0, 0);
        model.addAttribute("userCount", userService.getActiveUserCountByAcctType(Role.USER));
        model.addAttribute("creatorCount", userService.getActiveUserCountByAcctType(Role.CREATOR));
        model.addAttribute("adminCount", userService.getActiveUserCountByAcctType(Role.ADMIN));
        model.addAttribute("bannedCount", userService.getBannedUserCount());
        model.addAttribute("currentCount", userStatService.getCurrentLoginCount());
        model.addAttribute("statDisplay", usd);

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



}
