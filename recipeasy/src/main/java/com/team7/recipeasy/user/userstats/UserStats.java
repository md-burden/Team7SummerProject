package com.team7.recipeasy.user.userstats;

import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "userstats")
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userStatId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nonnull
    private Timestamp timestamp;

    public UserStats() {
    }

    public UserStats(int userStatId, User user, @Nonnull Timestamp timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.userStatId = userStatId;
    }

    public int getUserStatId() {
        return userStatId;
    }

    public void setUserStatId(int userStatId) {
        this.userStatId = userStatId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nonnull
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@Nonnull Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
