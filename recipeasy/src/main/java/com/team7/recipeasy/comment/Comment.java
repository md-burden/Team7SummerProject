package com.team7.recipeasy.comment;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @Nonnull
    private int recipeId;

    @Nonnull
    private int commenterId;

    @Nonnull
    private String commentContents;

    @NonNull
    private Timestamp postTime;

    public Comment() {
    }

    public Comment(int commentId, int recipeId, int commenterId, @Nonnull String commentContents, @NonNull Timestamp postTime) {
        this.commentId = commentId;
        this.recipeId = recipeId;
        this.commenterId = commenterId;
        this.commentContents = commentContents;
        this.postTime = postTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
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
