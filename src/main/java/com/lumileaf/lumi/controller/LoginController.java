package com.lumileaf.lumi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    /**
     * Handles the logout request.
     * We use PostMapping for better security,
     * but keep GetMapping as a fallback if needed.
     */
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Clear all session data (username, roles, etc.)
        session.invalidate();

        // Add a flash message to show on the login screen
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully.");

        return "redirect:/login?logout=true";
    }

    // Fallback for simple link-based logouts
    @GetMapping("/logout")
    public String logoutGet(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}