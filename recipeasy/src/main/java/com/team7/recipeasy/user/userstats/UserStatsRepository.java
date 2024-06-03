package com.team7.recipeasy.user.userstats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {
    @Query(value = "select count(*) from user_stats", nativeQuery = true)
    public int getTotalLogins();
}
