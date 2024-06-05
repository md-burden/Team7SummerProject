package com.team7.recipeasy;

import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.reports.ReportService;
import com.team7.recipeasy.user.UserService;
import com.team7.recipeasy.user.userstats.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @Autowired
    ReportService reportService;

    @Autowired
    UserService userService;

    @Autowired
    UserStatsService userStatService;

    @GetMapping(value = {"", "/", "/dashboard", "/home"})
    public String home(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "Base Pages/LoginPage";
    }
    @GetMapping("/ADMIN/home")
    public String getAdminHomePage(Model model){
        return "Admin/AdminHome";
    }
    @GetMapping("/ADMIN/reports")
    public String getAdminReportsPage(Model model){
        model.addAttribute("reportList", reportService.getAllReports());

        return "Admin/AdminReportsPage";
    }

    @GetMapping("/ADMIN/stats")
    public String getAdminStatsPage(Model model){
        model.addAttribute("userCount", userService.getActiveUserCountByAcctType(Role.USER));
        model.addAttribute("creatorCount", userService.getActiveUserCountByAcctType(Role.CREATOR));
        model.addAttribute("adminCount", userService.getActiveUserCountByAcctType(Role.ADMIN));
        model.addAttribute("bannedCount", userService.getBannedUserCount());
        model.addAttribute("currentCount", userStatService.getCurrentLoginCount());

        return "Admin/AdminStatsPage";
    }


}
