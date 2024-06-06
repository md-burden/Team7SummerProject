package com.team7.recipeasy.recipe;

import com.team7.recipeasy.constants.RecipeConstants;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recipeId;

    @Nonnull
    private String recipeTitle;

    private String recipeImage;

    @NonNull
    private String recipeCountry;

    @Nonnull
    private String recipeType;

    @Nonnull
    private String time;

    @NonNull
    private String yield;

    @Nonnull
    @Column(columnDefinition = "LONGTEXT")
    private String recipeInstructions;

    @Nonnull
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    private List<Ingredient> ingredients;

    @Nonnull
    private int totalSaves;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Used to update a users recipe
     * @param recipe
     * @param user
     */
    public Recipe(Recipe recipe, User user){
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeType = recipe.getRecipeType();
        this.time = recipe.getTime();
        this.yield = recipe.getYield();
        this.recipeInstructions = recipe.getRecipeInstructions();
        this.description = recipe.getDescription();
        this.totalSaves = recipe.getTotalSaves();
        this.recipeCountry = recipe.getRecipeCountry();
        this.user = user;

        if(recipe.getRecipeImage().isEmpty()){
            this.recipeImage = RecipeConstants.placeholder;
        }
        else{
            this.recipeImage = recipe.recipeImage;
        }
    }

    /**
     * Used to create a new recipe
     * @param recipe
     */
    public Recipe(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeImage = recipe.getRecipeImage();
        this.recipeCountry = recipe.getRecipeCountry();
        this.recipeType = recipe.getRecipeType();
        this.time = recipe.getTime();
        this.yield = recipe.getYield();
        this.recipeInstructions = recipe.getRecipeInstructions();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.totalSaves = recipe.getTotalSaves();
        this.user = recipe.getUser();
    }
}
