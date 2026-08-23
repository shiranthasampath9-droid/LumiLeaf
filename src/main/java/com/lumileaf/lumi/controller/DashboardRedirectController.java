package com.lumileaf.lumi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardRedirectController {

    @GetMapping("/dashboard")
    public String generalDashboard(HttpSession session) {
        String role = (String) session.getAttribute("role");

        // Redirecting triggers the logic in QAController to refresh dashboard card counts
        if ("QA".equals(role)) {
            return "redirect:/qa_dashboard";
        }

        return "redirect:/waiting_point_dashboard";
    }
}