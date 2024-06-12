package com.team7.recipeasy.user.favorites;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
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

    public Favorite(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public Favorite(int favoriteId, User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
        this.favoriteId = favoriteId;
    }
}
