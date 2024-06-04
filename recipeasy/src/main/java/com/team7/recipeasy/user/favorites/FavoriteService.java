package com.team7.recipeasy.user.favorites;

import com.team7.recipeasy.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository repo;

    @Autowired
    RecipeService recipeService;

    public List<Favorite> getAllFavorites(){
        return repo.findAll();
    }


    public int getFavoriteCountByRecipeId(int id) {
        return repo.getFavoritesCountByRecipe(id);
    }

    public int getFavoriteCountByUserId(int id) {
        int count = 0;
        List<Integer> recipeList = recipeService.getRecipeIdByUserId(id);
        for(Integer i: recipeList){
            count += repo.getFavoritesCountByRecipe(i);
        }
        return count;
    }
}
