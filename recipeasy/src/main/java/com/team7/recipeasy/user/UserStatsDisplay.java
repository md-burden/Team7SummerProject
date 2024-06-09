package com.team7.recipeasy.user;

/**
 * This Static class is used to display the creator and recipe statistics on the Admin stats page.
 */
public class UserStatsDisplay {
    private static String username = "";
    private static int totalCreatorSaves = 0;
    private static int totalCreatorComments = 0;


    private static String recipeTitle = "";
    private static int totalRecipeSaves = 0;
    private static int totalRecipeComments = 0;

    public UserStatsDisplay() {
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserStatsDisplay.username = username;
    }

    public static int getTotalCreatorSaves() {
        return totalCreatorSaves;
    }

    public static void setTotalCreatorSaves(int totalCreatorSaves) {
        UserStatsDisplay.totalCreatorSaves = totalCreatorSaves;
    }

    public static int getTotalCreatorComments() {
        return totalCreatorComments;
    }

    public static void setTotalCreatorComments(int totalCreatorComments) {
        UserStatsDisplay.totalCreatorComments = totalCreatorComments;
    }

    public static String getRecipeTitle() {
        return recipeTitle;
    }

    public static void setRecipeTitle(String recipeTitle) {
        UserStatsDisplay.recipeTitle = recipeTitle;
    }

    public static int getTotalRecipeSaves() {
        return totalRecipeSaves;
    }

    public static void setTotalRecipeSaves(int totalRecipeSaves) {
        UserStatsDisplay.totalRecipeSaves = totalRecipeSaves;
    }

    public static int getTotalRecipeComments() {
        return totalRecipeComments;
    }

    public static void setTotalRecipeComments(int totalRecipeComments) {
        UserStatsDisplay.totalRecipeComments = totalRecipeComments;
    }
}
