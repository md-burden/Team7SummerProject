package com.team7.recipeasy.reports;

import com.team7.recipeasy.comment.Comment;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reportId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Nonnull
    private String reportReason;

    public Report() {
    }

    public Report(Comment comment, int reportId, @Nonnull String reportReason) {
        this.comment = comment;
        this.reportId = reportId;
        this.reportReason = reportReason;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Nonnull
    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(@Nonnull String reportReason) {
        this.reportReason = reportReason;
    }
}
