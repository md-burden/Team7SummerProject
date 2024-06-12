package com.team7.recipeasy.user.favorites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(value = "select count(*) from favorites where recipe_id = ?1", nativeQuery = true)
    public int getFavoritesCountByRecipe(int recipeId);
}
