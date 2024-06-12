package com.team7.recipeasy.user.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team7.recipeasy.recipe.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;


    @GetMapping("/getCountRecipe/{id}")
    public int getFavoriteCountByRecipeId(@PathVariable int id) {
        return favoriteService.getFavoriteCountByRecipeId(id);
    }


    @GetMapping("/getCountUser/{id}")
    public int getFavoriteCountByUserId(@PathVariable int id) {
        return favoriteService.getFavoriteCountByUserId(id);
    }


    @PostMapping("/{userId}/{recipeId}")
    public ResponseEntity<Favorite> addFavorite(@PathVariable int userId, @PathVariable int recipeId) {
        Favorite favorite = favoriteService.addFavorite(userId, recipeId);
        return ResponseEntity.ok(favorite);
    }


    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable int userId, @PathVariable int recipeId) {
        favoriteService.removeFavorite(userId, recipeId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recipe>> getFavoriteRecipes(@PathVariable int userId) {
        List<Recipe> favoriteRecipes = favoriteService.getFavoriteRecipes(userId);
        return ResponseEntity.ok(favoriteRecipes);
    }
}
