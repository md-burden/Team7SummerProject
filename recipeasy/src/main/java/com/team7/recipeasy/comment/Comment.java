
package com.team7.recipeasy.comment;

import com.team7.recipeasy.recipe.Recipe;
import com.team7.recipeasy.user.User;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private int connectedId;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @Nonnull
    private String commentContents;

    @CreationTimestamp
    @Column(name="post_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp postTime;

    public Comment() {
    }

    public Comment(int commentId, Recipe recipe, User commenter, int connectedId, @Nonnull String commentContents, @NonNull Timestamp postTime) {
        this.commentId = commentId;
        this.recipe = recipe;
        this.commenter = commenter;
        this.commentContents = commentContents;
        this.postTime = postTime;
        this.connectedId = connectedId;
    }

    public int getConnectedId() {
        return connectedId;
    }

    public void setConnectedId(int connectedId) {
        this.connectedId = connectedId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    @Nonnull
    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(@Nonnull String commentContents) {
        this.commentContents = commentContents;
    }

    @NonNull
    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(@NonNull Timestamp postTime) {
        this.postTime = postTime;
    }

}
