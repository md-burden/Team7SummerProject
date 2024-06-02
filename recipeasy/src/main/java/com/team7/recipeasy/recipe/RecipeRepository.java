package com.team7.recipeasy.recipe;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value = "SELECT * FROM recipe WHERE user_id = :userId LIMIT 5", nativeQuery = true)
    List<Recipe> findCreatorRecent(@Param("userId") int userId);
}
