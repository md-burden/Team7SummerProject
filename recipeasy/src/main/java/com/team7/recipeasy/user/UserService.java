package com.team7.recipeasy.user;

import com.team7.recipeasy.constants.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }


    public int getBannedUserCount(){
        return userRepository.getTotalBannedUserCount();
    }

    public List<User> getUsersFromSearch(String term) {
        System.out.println("term " + term);
        System.out.println(userRepository.getUsersBySearch(term).toString());
        return userRepository.getUsersBySearch(term);
    }
}
