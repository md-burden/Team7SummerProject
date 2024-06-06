package com.team7.recipeasy.user;

import com.team7.recipeasy.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT COUNT(user_id) FROM user WHERE role = ?1 AND is_active_user = 1", nativeQuery = true)
    int getActiveUserCountByAcctType(int role);

    @Query(value = "SELECT COUNT(user_id) FROM user WHERE is_active_user = 0", nativeQuery = true)
    int getTotalBannedUserCount();

    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Optional<User> getUserByUsername(String username);

    @Query(value = "Select * FROM user WHERE username like %?1% limit 5", nativeQuery = true)
    List<User> getUsersBySearch(String username);


}
