package com.team7.recipeasy.user.favorites;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.user.User;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;


    @Autowired
    private UserService userService;


    @Autowired
    private RecipeService recipeService;


    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }


    public int getFavoriteCountByRecipeId(int recipeId) {
        return favoriteRepository.getFavoritesCountByRecipe(recipeId);
    }


    public int getFavoriteCountByUserId(int userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.size();
    }


    public Favorite addFavorite(int userId, int recipeId) {
        User user = userService.getUserById(userId);
        Recipe recipe = recipeService.getRecipeById(recipeId);


        Favorite favorite = new Favorite(user, recipe);
        return favoriteRepository.save(favorite);
    }


    public void removeFavorite(int userId, int recipeId) {
        Favorite favorite = favoriteRepository.findByUserIdAndRecipeId(userId, recipeId);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        }
    }


    public List<Recipe> getFavoriteRecipes(int userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream().map(Favorite::getRecipe).collect(Collectors.toList());
    }
}
