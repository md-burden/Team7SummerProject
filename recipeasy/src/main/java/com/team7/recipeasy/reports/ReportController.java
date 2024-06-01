package com.team7.recipeasy.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReportController {
    @Autowired
    ReportService reportService;

}
