package com.team7.recipeasy.recipe;

import com.team7.recipeasy.constants.RecipeConstants;
import com.team7.recipeasy.recipe.ingredients.Ingredient;
import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
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

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @Nonnull
    private int totalSaves;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Recipe(){
        this.ingredients = Arrays.asList(new Ingredient[10]);
    }

    // Constructor for updating a recipe (adjust based on your needs)
    public Recipe(Recipe recipe, User user, List<Ingredient> ingredients) {
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeId = recipe.recipeId;
        this.recipeCountry = recipe.recipeCountry;
        this.recipeType = recipe.recipeType;
        this.time = recipe.time;
        this.yield = recipe.yield;
        this.recipeInstructions = recipe.recipeInstructions;
        this.totalSaves = recipe.totalSaves;
        this.user = user;

        if( recipe.getRecipeImage() == null || recipe.getRecipeImage().isEmpty()){
            this.recipeImage = RecipeConstants.placeholder;
        }
        else{
            this.recipeImage = recipe.recipeImage;
        }

        // Only add ingredients with values
        for(Ingredient ingredient : recipe.getIngredients()) {
            if (!(ingredient == null) && !(ingredient.getIngredientName().isEmpty())) {
                addIngredient(ingredient);
            }
        }
    }

    // Constructor for creating a new recipe
    public Recipe(String recipeTitle, String recipeImage, String recipeCountry, String recipeType,
                  String time, String yield, String recipeInstructions, User user) {
        this.recipeTitle = recipeTitle;
        this.recipeImage = recipeImage;
        this.recipeCountry = recipeCountry;
        this.recipeType = recipeType;
        this.time = time;
        this.yield = yield;
        this.recipeInstructions = recipeInstructions;
        this.user = user;
        this.totalSaves = 0;


    }

    // Helper method for adding ingredients
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
