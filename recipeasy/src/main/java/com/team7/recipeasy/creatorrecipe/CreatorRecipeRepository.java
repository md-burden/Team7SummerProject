package com.team7.recipeasy.creatorrecipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRecipeRepository extends JpaRepository<CreatorRecipe, Integer> {
}
