package com.team7.recipeasy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/byCommentId/{id}")
    public Comment fetchCommentById(@PathVariable int id){
        return commentService.fetchCommentById(id);
    }

    @GetMapping("/byRecipeId/{id}")
    public List<Comment> fetchAllCommentsByRecipeId(@PathVariable int id){
        return commentService.fetchAllCommentsByRecipeId(id);
    }

    @GetMapping("/all")
    public List<Comment> fetchAllComments(){
        return commentService.fetchAllComments();
    }

    @PostMapping("/create")
    public void createComment(@RequestBody Comment comment){
        commentService.saveComment(comment);
    }

    @PutMapping("/update")
    public void updateComment(@RequestBody Comment comment){
        commentService.saveComment(comment);
    }

    @DeleteMapping("/removeInfo/{id}")
    public void removeCommentInfo(@PathVariable int id){
        commentService.removeCommentInfo(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(@PathVariable int id){
        commentService.deleteCommentById(id);
    }

    @DeleteMapping("/deleteBlock/{id}")
    public void deleteCommentBlock(@PathVariable int id){
        commentService.deleteCommentReplyBlock(id);
    }

    @GetMapping("/stats/totalCommentsByUser/{id}")
    public int getTotalCommentCountByUser(@PathVariable int id){
        return commentService.getCommentCountByUser(id);
    }

    @GetMapping("/stats/totalCommentsByRecipe/{id}")
    public int getTotalCommentCountByRecipe(@PathVariable int id){
        return commentService.getCommentCountByRecipe(id);
    }

}
