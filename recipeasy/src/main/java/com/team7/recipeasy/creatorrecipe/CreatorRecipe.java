package com.team7.recipeasy.creatorrecipe;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.user.User;
import jakarta.persistence.*;

@Entity
@Table(name="creator_recipe")
public class CreatorRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int creatorRecipeId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    public CreatorRecipe() {
    }

    public CreatorRecipe(int id, User user, Recipe recipe) {
        this.creatorRecipeId = id;
        this.user = user;
        this.recipe = recipe;
    }

    public int getCreatorRecipeId() {
        return creatorRecipeId;
    }

    public void setCreatorRecipeId(int creatorRecipeId) {
        this.creatorRecipeId = creatorRecipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
