package com.team7.recipeasy.user.userstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatsService {

    @Autowired
    UserStatsRepository repo;
}

