package com.team7.recipeasy.recipe;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {


    // Gets 5 newest recipes for a given user id
    @Query(value = "SELECT * FROM recipe WHERE user_id = :userId ORDER BY recipe_id DESC LIMIT 10;", nativeQuery = true)
    List<Recipe> findCreatorRecent(@Param("userId") int userId);

    @Query(value = "SELECT total_saves FROM recipe WHERE recipe_id = :recipeId", nativeQuery = true)
    String getRecipeStats(@Param("recipeId") int recipeId);

    @Query(value = "SELECT * FROM recipe WHERE user_id = :userId", nativeQuery = true)
    List<Recipe> findAllCreatorRecipe(@Param("userId") int userId);
  
    @Query(value = "SELECT recipe_id FROM recipe WHERE user_id = ?1", nativeQuery = true)
    List<Integer> getRecipeIdByUserId(int userId);

    @Query(value = "SELECT recipe_id FROM recipe WHERE recipe_title = ?1", nativeQuery = true)
    Integer getRecipeIdByRecipeName(String recipeName);

    @Query(value = "SELECT COUNT(*) FROM recipe WHERE user_id = :userId", nativeQuery = true)
    int getRecipeCountByUserId(int userId);

    @Query(value = "SELECT * FROM recipe WHERE recipe_title LIKE %:keyword%", nativeQuery = true)
    List<Recipe> searchRecipesByTitle(@Param("keyword") String keyword);
}
