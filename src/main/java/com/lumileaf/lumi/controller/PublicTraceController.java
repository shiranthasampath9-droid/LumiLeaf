package com.lumileaf.lumi.controller;

import com.lumileaf.lumi.service.TraceabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicTraceController {

    @Autowired private TraceabilityService traceService;

    @GetMapping("/trace/{fgNumber}")
    public String viewTraceability(@PathVariable String fgNumber, Model model) {
        try {
            model.addAttribute("data", traceService.getProductHistory(fgNumber));
            model.addAttribute("fgNumber", fgNumber);
            return "public_traceability";
        } catch (Exception e) {
            return "redirect:/login?error=notfound";
        }
    }
}