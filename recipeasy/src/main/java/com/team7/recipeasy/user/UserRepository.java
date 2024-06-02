package com.team7.recipeasy.user;

import com.team7.recipeasy.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT COUNT(user_id) FROM user WHERE role = ?1", nativeQuery = true)
    int getActiveUserCountByAcctType(int role);
}
