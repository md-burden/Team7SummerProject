package com.team7.recipeasy.user;


import com.fasterxml.jackson.databind.JsonNode;
import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.themealdb.Meal;
import com.team7.recipeasy.themealdb.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    MealService mealService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        user.setActiveUser(true);
        userService.saveUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        return userService.getUserById(id);
    }


    @PutMapping("/ban/{id}")
    public void banUserById(@PathVariable int id){
        userService.BanUserById(id);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }

    @GetMapping("/stats/getCount")
    public int getActiveUserCountByAcctType(@RequestBody Role r){
        return userService.getActiveUserCountByAcctType(r);
    }

    @GetMapping("/stats/getBannedCount")
    public int getBannedUserCount(){
        return userService.getBannedUserCount();
    }

    @GetMapping("/home")
    public String userHomePage() {
        return "User/UserHomePage";
    }


    @GetMapping("/profile")
    public String getUserProfilePage() {
        return "User/UserProfilePage";
    }

    @GetMapping("/recipePage")
    public String getRecipePage(@RequestParam String id, Model model) {
        Meal meal = mealService.getMealById(id);
        model.addAttribute("meal", meal);
        return "User/UserRecipePage";
    }

    @GetMapping("/search")
    public String getSearchRecipes (@RequestParam(value = "name", required = false) String name, Model model) {
        List<Meal> recipes = mealService.searchMealByName(name);
        System.out.println(recipes);
        model.addAttribute("recipes", recipes);
        return "User/UserResultsPage";
    }




}
