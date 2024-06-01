package com.team7.recipeasy.user.favorites;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Favorite() {
    }

    public Favorite(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
