package com.team7.recipeasy.recipe;

import com.team7.recipeasy.recipe.ingredients.Ingredient;
import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recipeId;

    @Nonnull
    private String recipeTitle;

    @Nonnull
    private String recipeType;

    @Nonnull
    private double time;

    @NonNull
    private double yield;

    @Nonnull
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

    @Nonnull
    private String recipeCountry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Recipe() {
    }

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
        this.ingredients = recipe.getIngredients();
        this.user = user;
    }

    public Recipe(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeType = recipe.getRecipeType();
        this.time = recipe.getTime();
        this.yield = recipe.getYield();
        this.recipeInstructions = recipe.getRecipeInstructions();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.totalSaves = recipe.getTotalSaves();
        this.recipeCountry = recipe.getRecipeCountry();
        this.user = recipe.getUser();
    }

    public Recipe(int recipeId, @Nonnull String recipeTitle, @Nonnull String recipeType, double time, double yield, @Nonnull String recipeInstructions, int totalSaves, @Nonnull String recipeCountry, User user, List<Ingredient> ingredients, @Nonnull String description) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipeType = recipeType;
        this.time = time;
        this.yield = yield;
        this.recipeInstructions = recipeInstructions;
        this.totalSaves = totalSaves;
        this.recipeCountry = recipeCountry;
        this.user = user;
        this.ingredients = ingredients;
        this.description = description;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Nonnull
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(@Nonnull String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    @Nonnull
    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(@Nonnull String recipeType) {
        this.recipeType = recipeType;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    @Nonnull
    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(@Nonnull String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nonnull String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getTotalSaves() {
        return totalSaves;
    }

    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }

    @Nonnull
    public String getRecipeCountry() {
        return recipeCountry;
    }

    public void setRecipeCountry(@Nonnull String recipeCountry) {
        this.recipeCountry = recipeCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
