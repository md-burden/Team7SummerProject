package com.team7.recipeasy.user.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService service;
}
