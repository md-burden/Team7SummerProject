package com.team7.recipeasy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

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

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
