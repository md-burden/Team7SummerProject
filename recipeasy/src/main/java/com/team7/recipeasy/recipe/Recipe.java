package com.team7.recipeasy.recipe;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name="recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recipeId;

    @Nonnull
    private int totalSaves;

    @Nonnull
    private String recipeType;

    @Nonnull
    private String recipeCountry;

    @Nonnull
    private String recipeTitle;

    @Nonnull
    private String recipeInstructions;

    @OneToMany
    @JoinColumn(name="creatorId")
    private int Creator;

    public Recipe() {
    }

    public Recipe(int recipeId, int totalSaves, @Nonnull String recipeType, @Nonnull String recipeCountry, @Nonnull String recipeTitle, @Nonnull String recipeInstructions, int creator) {
        this.recipeId = recipeId;
        this.totalSaves = totalSaves;
        this.recipeType = recipeType;
        this.recipeCountry = recipeCountry;
        this.recipeTitle = recipeTitle;
        this.recipeInstructions = recipeInstructions;
        Creator = creator;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getTotalSaves() {
        return totalSaves;
    }

    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }

    @Nonnull
    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(@Nonnull String recipeType) {
        this.recipeType = recipeType;
    }

    @Nonnull
    public String getRecipeCountry() {
        return recipeCountry;
    }

    public void setRecipeCountry(@Nonnull String recipeCountry) {
        this.recipeCountry = recipeCountry;
    }

    @Nonnull
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(@Nonnull String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    @Nonnull
    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(@Nonnull String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public int getCreator() {
        return Creator;
    }

    public void setCreator(int creator) {
        Creator = creator;
    }
}
