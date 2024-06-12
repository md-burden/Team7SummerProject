package com.team7.recipeasy.user;

import com.team7.recipeasy.constants.Role;
import com.team7.recipeasy.recipe.RecipeService;
import com.team7.recipeasy.user.favorites.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void BanUserById(int id){
        User u = userRepository.findById(id).orElse(null);
        if(u != null){
            u.setActiveUser(false);
            userRepository.save(u);
        }
    }
    public Optional<User> findUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.getUserByUsername(username);
    }

    public int getActiveUserCountByAcctType(Role r){

        return userRepository.getActiveUserCountByAcctType(r.ordinal());
    }

    public void deleteUserById(int userId){
        userRepository.deleteById(userId);
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user){
        user.setActiveUser(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void resetPassword(User user){
        User u = userRepository.getUserByUsername(user.getUsername()).orElse(null);
        if(u != null){
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(u);
        }
    }

    public int getBannedUserCount(){
        return userRepository.getTotalBannedUserCount();
    }

    public List<User> getUsersFromSearch(String term) {
        System.out.println("term " + term);
        System.out.println(userRepository.getUsersBySearch(term).toString());
        return userRepository.getUsersBySearch(term);
    }

//    public void setUserFavorite (int userId, int recipeId) {
//        if(favoriteService.isFavoriteRecipe(userId, recipeId)) {
//            favoriteService.addFavorite(userId, recipeId);
//        } else {
//            favoriteService.removeFavorite(userId, recipeId);
//        }
//    }
}
