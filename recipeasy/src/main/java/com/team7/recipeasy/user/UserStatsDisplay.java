package com.team7.recipeasy.user;

public class UserStatsDisplay {
    private String username;
    private int totalCreatorSaves;
    private int totalCreatorComments;
    private int recentCreatorSaves;

    private String recipeTitle;
    private int totalRecipeSaves;
    private int totalRecipeComments;
    private int recentRecipeSaves;

    public UserStatsDisplay() {
    }

    public UserStatsDisplay(String username, int totalCreatorSaves, int totalCreatorComments, int recentCreatorSaves, String recipeTitle, int totalRecipeSaves, int totalRecipeComments, int recentRecipeSaves) {
        this.username = username;
        this.totalCreatorSaves = totalCreatorSaves;
        this.totalCreatorComments = totalCreatorComments;
        this.recentCreatorSaves = recentCreatorSaves;
        this.recipeTitle = recipeTitle;
        this.totalRecipeSaves = totalRecipeSaves;
        this.totalRecipeComments = totalRecipeComments;
        this.recentRecipeSaves = recentRecipeSaves;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalCreatorSaves() {
        return totalCreatorSaves;
    }

    public void setTotalCreatorSaves(int totalCreatorSaves) {
        this.totalCreatorSaves = totalCreatorSaves;
    }

    public int getTotalCreatorComments() {
        return totalCreatorComments;
    }

    public void setTotalCreatorComments(int totalCreatorComments) {
        this.totalCreatorComments = totalCreatorComments;
    }

    public int getRecentCreatorSaves() {
        return recentCreatorSaves;
    }

    public void setRecentCreatorSaves(int recentCreatorSaves) {
        this.recentCreatorSaves = recentCreatorSaves;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public int getTotalRecipeSaves() {
        return totalRecipeSaves;
    }

    public void setTotalRecipeSaves(int totalRecipeSaves) {
        this.totalRecipeSaves = totalRecipeSaves;
    }

    public int getTotalRecipeComments() {
        return totalRecipeComments;
    }

    public void setTotalRecipeComments(int totalRecipeComments) {
        this.totalRecipeComments = totalRecipeComments;
    }

    public int getRecentRecipeSaves() {
        return recentRecipeSaves;
    }

    public void setRecentRecipeSaves(int recentRecipeSaves) {
        this.recentRecipeSaves = recentRecipeSaves;
    }
}
