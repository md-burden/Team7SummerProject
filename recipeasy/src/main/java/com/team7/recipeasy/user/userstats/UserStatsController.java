package com.team7.recipeasy.user.userstats;

import com.team7.recipeasy.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logInStats")
public class UserStatsController {
    @Autowired
    UserStatsService statsService;

    @PostMapping("/login")
    public void logInStats(@RequestBody UserStats u){
        statsService.saveUserStats(u);
    }

    @DeleteMapping("/logout")
    public void logOutStats(@RequestBody UserStats u){
        statsService.deleteUserStats(u);
    }

}
