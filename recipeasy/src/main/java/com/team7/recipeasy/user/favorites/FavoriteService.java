package com.team7.recipeasy.user.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository repo;

    public List<Favorite> getAllFavorites(){
        return repo.findAll();
    }


}
