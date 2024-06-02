package com.team7.recipeasy.user.userstats;

import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="user_stats")
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userStatId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Nonnull
    private Timestamp timestamp;

    public UserStats() {}

    public UserStats(int userStatId, User user, @Nonnull Timestamp timestamp) {
        this.userStatId = userStatId;
        this.user = user;
        this.timestamp = timestamp;
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
