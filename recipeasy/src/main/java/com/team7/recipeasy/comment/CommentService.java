package com.team7.recipeasy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public void removeCommentInfo(int id){
        Comment comment = fetchCommentById(id);
        comment.setCommenterId(-1);
        comment.setCommentContents("Comment removed by Admin");
        commentRepository.save(comment);
    }

    public void deleteCommentReplyBlock(int id){
        Comment comment = fetchCommentById(id);
        commentRepository.deleteById(comment.getConnectedId());
        commentRepository.deleteById(comment.getCommentId());

    }

}
