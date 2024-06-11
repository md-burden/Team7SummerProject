package com.team7.recipeasy.user.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(value = "select count(*) from favorites where recipe_id = ?1", nativeQuery = true)
    int getFavoritesCountByRecipe(int recipeId);

    @Query("SELECT f FROM Favorite f WHERE f.user.userId = :userId")
    List<Favorite> findByUserId(int userId);


    @Query("SELECT f FROM Favorite f WHERE f.user.userId = :userId AND f.recipe.recipeId = :recipeId")
    Favorite findByUserIdAndRecipeId(int userId, int recipeId);



}
