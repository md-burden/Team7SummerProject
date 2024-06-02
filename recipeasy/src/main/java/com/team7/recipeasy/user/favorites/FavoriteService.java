package com.team7.recipeasy.user.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository repo;
}
