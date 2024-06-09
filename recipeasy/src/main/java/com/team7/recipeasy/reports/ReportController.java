package com.team7.recipeasy.reports;

import com.team7.recipeasy.constants.ReportOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/ADMIN/reports/deleteBan/{reportId}")
    public String deleteAndBan(@PathVariable int reportId){
        if(reportService.findReportById(reportId).getComment().getConnectedId() < 0){
            reportService.handleReport(reportId, ReportOptions.DELETE_BLOCK_AND_BAN);
        }
        else{
            reportService.handleReport(reportId, ReportOptions.REMOVE_TEXT_AND_BAN);
        }
        return "redirect:/ADMIN/reports";
    }
    @GetMapping("/ADMIN/reports/delete/{reportId}")
    public String deleteComment(@PathVariable int reportId){
        if(reportService.findReportById(reportId).getComment().getConnectedId() < 0){
            reportService.handleReport(reportId, ReportOptions.DELETE_BLOCK);
        }
        else{
            reportService.handleReport(reportId, ReportOptions.REMOVE_TEXT);
        }
        return "redirect:/ADMIN/reports";
    }
    @GetMapping("/ADMIN/reports/falseReport/{reportId}")
    public String falseReport(@PathVariable int reportId){
        reportService.handleReport(reportId, ReportOptions.FALSE_REPORT);
        return "redirect:/ADMIN/reports";
    }


}
