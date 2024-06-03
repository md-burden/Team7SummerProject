package com.team7.recipeasy.user.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    FavoriteService service;

    @GetMapping("/getCountRecipe/{id}")
    public int getFavoriteCountByRecipeId(@PathVariable int id){
        return service.getFavoriteCountByRecipeId(id);
    }

    @GetMapping("/getCountUser/{id}")
    public int getFavoriteCountByUserId(@PathVariable int id){
        return service.getFavoriteCountByUserId(id);
    }
}
