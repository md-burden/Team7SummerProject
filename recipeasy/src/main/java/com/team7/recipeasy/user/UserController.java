package com.team7.recipeasy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
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
}
