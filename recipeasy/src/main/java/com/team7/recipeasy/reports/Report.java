package com.team7.recipeasy.reports;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @Nonnull
    private int reportId;

    @Nonnull
    private String reportReason;

    public Report() {
    }

    public Report(int commentId, int reportId, @Nonnull String reportReason) {
        this.commentId = commentId;
        this.reportId = reportId;
        this.reportReason = reportReason;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
