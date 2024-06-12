package com.team7.recipeasy.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT * FROM reports WHERE comment_id = :commentId", nativeQuery = true)
    public List<Report> findReportsByCommentId(@Param("commentId") int commentId);
}
