package com.team7.recipeasy.user.favorites;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int favoriteId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Favorite() {
    }

    public Favorite(int favoriteId, User user, Recipe recipe) {
        this.favoriteId = favoriteId;
        this.user = user;
        this.recipe = recipe;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
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
