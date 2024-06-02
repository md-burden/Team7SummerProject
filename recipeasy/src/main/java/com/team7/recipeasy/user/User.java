package com.team7.recipeasy.user;

import com.team7.recipeasy.constants.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Nonnull
    private Role role;

    @Nonnull
    private String email;

    @Nonnull
    private String password;

    @Nonnull
    private String preference;

    private boolean isActiveUser;

    public User() {
    }

    public User(int userId, @Nonnull Role role, @Nonnull String email, @Nonnull String password, @Nonnull String preference, boolean isActiveUser) {
        this.userId = userId;
        this.role = role;
        this.email = email;
        this.password = password;
        this.preference = preference;
        this.isActiveUser = isActiveUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Nonnull
    public Role getRole() {
        return role;
    }

    public void setRole(@Nonnull Role role) {
        this.role = role;
    }

    @Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nonnull String email) {
        this.email = email;
    }

    @Nonnull
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    @Nonnull
    public String getPreference() {
        return preference;
    }

    public void setPreference(@Nonnull String preference) {
        this.preference = preference;
    }

    public boolean isActiveUser() {
        return isActiveUser;
    }

    public void setActiveUser(boolean activeUser) {
        isActiveUser = activeUser;
    }
}
