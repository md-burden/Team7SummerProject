package com.team7.recipeasy.user.userstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStatsController {
    @Autowired
    UserStatsService statsService;

}
