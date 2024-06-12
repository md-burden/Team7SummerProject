package com.team7.recipeasy.user;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.themealdb.Meal;
import com.team7.recipeasy.themealdb.MealService;
import com.team7.recipeasy.user.favorites.Favorite;
import com.team7.recipeasy.user.favorites.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    MealService mealService;

    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public String createUser(User user){
        user.setActiveUser(true);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/login";
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        return userService.getUserById(id);
    }


    @PostMapping("/ban/{id}")
    public void banUserById(@PathVariable int id){
        userService.BanUserById(id);
    }

    @GetMapping("/deleteAccount/{id}")
    public String deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
        return "redirect:/login";
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
    public String getUserProfilePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null));
        model.addAttribute("user", user);
        return "User/UserProfilePage";
    }

    @GetMapping("/recipePage")
    public String getRecipePage(@RequestParam String id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Meal meal = mealService.getMealById(id);
//        User user = Objects.requireNonNull(userService.findUserByUsername(userDetails.getUsername()).orElse(null));
//        model.addAttribute("user", user);
//        model.addAttribute("mealId", parseInt(meal.getIdMeal()));
//        if(favoriteService.isFavoriteRecipe(user.getUserId(), parseInt(id))) {
//            model.addAttribute("favorite", true);
//        } else {
//            model.addAttribute("favorite", false);
//        }

        if(meal != null) {
            model.addAttribute("meal", meal);
            return "User/UserRecipePage";
        }
        Recipe recipe = recipeService.getRecipeById(parseInt(id));
        model.addAttribute("recipe", recipe);
        return "User/UserDBRecipePage";

    }

    @GetMapping("/search")
    public String getSearchRecipes (@RequestParam(value = "name", required = false) String name, Model model) {
        List<Meal> recipes = mealService.searchMealByName(name);
        System.out.println(recipes);
        model.addAttribute("recipes", recipes);
        return "User/UserResultsPage";
    }

    @GetMapping("/favorites")
    public String getFavorites () {
        return "User/Favorites";
    }

}
