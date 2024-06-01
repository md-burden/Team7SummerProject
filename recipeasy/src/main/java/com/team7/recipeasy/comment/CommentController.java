package com.team7.recipeasy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    public void removeCommentInfo(int id){
        commentService.removeCommentInfo(id);
    }
}