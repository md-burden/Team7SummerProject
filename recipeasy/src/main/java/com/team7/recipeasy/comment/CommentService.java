package com.team7.recipeasy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void deleteCommentById(int id){
        commentRepository.deleteById(id);
    }

    public Comment fetchCommentById(int id){
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> fetchAllComments(){
        return commentRepository.findAll();
    }

    public List<Comment> fetchAllCommentsByRecipeId(int id){
        return commentRepository.getCommentsByRecipe(id);
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public void removeCommentInfo(int id){
        Comment comment = fetchCommentById(id);
        comment.setCommentContents("Comment removed by Admin");
        commentRepository.save(comment);
    }

    public void deleteCommentReplyBlock(int id){
        Comment comment = fetchCommentById(id);
        commentRepository.deleteById(comment.getConnectedId());
        commentRepository.deleteById(comment.getCommentId());

    }

    public void deleteCommentsByRecipeId(int recipeId){
        List<Comment> comments = commentRepository.getCommentsByRecipe(recipeId);
        for(Comment comment : comments){
            deleteCommentReplyBlock(comment.getCommentId());
        }
    }

    public int getCommentCountByRecipe(int recipeId){
        return commentRepository.getCommentCountByRecipe(recipeId);
    }

    public int getCommentCountByUser(int userId){
        return commentRepository.getCommentCountByUser(userId);
    }

    public int getReplyCountByCommentId(int commentId){
        return commentRepository.getReplyCountByCommentID(commentId);
    }

}
