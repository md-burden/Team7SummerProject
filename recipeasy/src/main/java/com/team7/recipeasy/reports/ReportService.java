package com.team7.recipeasy.reports;

import com.team7.recipeasy.comment.Comment;
import com.team7.recipeasy.comment.CommentService;
import com.team7.recipeasy.constants.ReportOptions;
import com.team7.recipeasy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    public Report findReportById(int id){
        return reportRepository.findById(id).orElse(null);
    }

    public void deleteReportById(int id){
        reportRepository.deleteById(id);
    }

    /**
     * Handles reports based on Enum parameter
     * REMOVE_TEXT: simply removes the comment text and associated commenter id, but
     * keeps the comment intact
     * DELETE_BLOCK: deletes the comment block, including both review and reply
     * DELETE_BLOCK_AND_BAN: deletes the comment block and bans the user of the reported comment
     * FALSE_REPORT: does nothing to the comments
     * All options delete the report in the process
     * @param id
     * @param option
     */
    public void handleReport(int id, ReportOptions option){
        int commentId = -1;
        Comment c = null;
        Report r = reportRepository.findById(id).orElse(null);
        if(r != null){
            c = r.getComment();
        }
        if(c != null){
            commentId = c.getCommentId();
        }
        //Delete report before handling comments to prevent foreign key errors
        reportRepository.deleteById(id);

        switch(option){
            //Removes just the text and ID of the commenter
            case REMOVE_TEXT:
                commentService.removeCommentInfo(commentId);
                break;

            //Deletes comment/reply block
            case DELETE_BLOCK:
                commentService.deleteCommentReplyBlock(commentId);
                break;

            case REMOVE_TEXT_AND_BAN:
                commentService.removeCommentInfo(commentId);
                userService.BanUserById(c.getCommenter().getUserId());
                break;

            //Deletes comment/reply block and bans user
            case DELETE_BLOCK_AND_BAN:
                commentService.deleteCommentReplyBlock(commentId);
                userService.BanUserById(c.getCommenter().getUserId());
                break;
            //Does nothing in case of false report
            case FALSE_REPORT:
                break;

        }
    }

    public void saveReport(Report report){
        reportRepository.save(report);
    }

}
