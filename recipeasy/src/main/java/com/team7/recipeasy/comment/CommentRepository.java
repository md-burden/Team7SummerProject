package com.team7.recipeasy.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    /**
     * Fetches all comments with specified recipe ID
     * @param recipeId id to search for
     * @return a list of comments
     */
    @Query(value = "select * from comment where recipe_id = ?1", nativeQuery = true)
    public List<Comment> getCommentsByRecipe(int recipeId);

    @Query(value = "select count(*) from comment where recipe_id = ?1", nativeQuery = true)
    public int getCommentCountByRecipe(int recipeId);

    @Query(value = "select count(*) from comment where commenter_id = ?1", nativeQuery = true)
    public int getCommentCountByUser(int userId);

    @Query(value = "select count(*) from comment where connected_id = ?1", nativeQuery = true)
    public int getReplyCountByCommentID(int commentID);
}
